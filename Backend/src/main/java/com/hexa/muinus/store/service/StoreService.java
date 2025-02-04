package com.hexa.muinus.store.service;

import com.hexa.muinus.common.exception.store.BoardForbiddenException;
import com.hexa.muinus.common.exception.store.StoreLocationDuplicateException;
import com.hexa.muinus.common.exception.store.StoreNotFoundException;
import com.hexa.muinus.common.exception.UserNotFoundException;
import com.hexa.muinus.common.exception.store.StoreNotForbiddentException;
import com.hexa.muinus.store.domain.information.Announcement;
import com.hexa.muinus.store.domain.information.respository.AnnouncementRepository;
import com.hexa.muinus.store.domain.item.repository.FliItemRepository;
import com.hexa.muinus.store.domain.item.repository.StoreItemRepository;
import com.hexa.muinus.store.domain.store.Store;
import com.hexa.muinus.store.dto.information.AnnouncementDTO;
import com.hexa.muinus.store.dto.information.AnnouncementDeleteDTO;
import com.hexa.muinus.store.dto.information.AnnouncementModifyDTO;
import com.hexa.muinus.store.dto.information.AnnouncementWriteDTO;
import com.hexa.muinus.store.dto.store.*;
import com.hexa.muinus.store.domain.store.repository.StoreRepository;
import com.hexa.muinus.users.domain.user.Users;
import com.hexa.muinus.users.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final StoreItemRepository storeItemRepository;
    private final FliItemRepository fliItemRepository;


    /**
     * 매장 등록
     * @param storeRegisterDTO 매장 등록 정보
     */
    @Transactional("dataTransactionManager")
    public void registerStore(StoreRegisterDTO storeRegisterDTO) {
        log.info("Starting store registration for DTO: {}", storeRegisterDTO);

        // 사용자 유효성 검사
        Users user = userRepository.findByEmail(storeRegisterDTO.getUserEmail());
        if (user == null) {
            throw new UserNotFoundException(storeRegisterDTO.getUserEmail());
        }

        // 주소 중복 확인
        if ((storeRepository.findByLocationXAndLocationY(storeRegisterDTO.getLocationX(), storeRegisterDTO.getLocationY())).isPresent()) {
            throw new StoreLocationDuplicateException(storeRegisterDTO.getLocationX(), storeRegisterDTO.getLocationY());
        }

        Store store = storeRegisterDTO.toEntity(user);
        System.out.println("Convered: " + store);

        log.debug("Converted StoreRegistDTO to Store entity: {}", store);

        saveStore(store);
    }

    /**
     * Insert Store
     * 
     * @param store Store 객체
     * @return storeNo 추가된 Store 객체
     */
    private Store saveStore(Store store) {
        System.out.println("Saving store: " + store);
        Store savedStore = storeRepository.save(store);
        log.info("Store saved successfully with ID: {}", savedStore.getStoreNo());
        return savedStore;
    }

    /**
     * 매장 삭제
     * - `deleted` 컬럼 값 "Y"-> "N" 으로 변경
     * - 해당 매장 비활성화
     * @param storeNo 매장 번호
     */
    @Transactional
    public void closeStore(int storeNo) {
        log.info("Closing store with No: {}", storeNo);
        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new StoreNotFoundException(storeNo));

        store.disableStore();
        log.info("Store {} has been disabled successfully (deleted={})", store.getStoreNo(), store.getDeleted());
    }


    /**
     * 매장 정보 수정
     * @param storeModifyDTO 수정할 매장 정보
     */
    @Transactional
    public void modifyStore(StoreModifyDTO storeModifyDTO) {
        log.info("Modifying store with userEmail: {}", storeModifyDTO.getUserEmail());

        // 사용자 - 매장 조회
        Store store = storeRepository.findByUser_Email(storeModifyDTO.getUserEmail())
                .orElseThrow(() -> new StoreNotForbiddentException(storeModifyDTO.getUserEmail()));

        // 매장 정보 수정
        store.updateStoreInfo(storeModifyDTO);
        log.info("Store {} has been updated successfully ({})", store.getStoreNo(), store);
    }

    /**
     * 해당 제품을 판매하는 내 주변 매장 조회
     * @param itemId 제품 번호
     * @return List<StoreSearchDTO> stores
     */
    @Transactional(readOnly = true)
    public List<StoreSearchDTO> searchStore(int itemId, BigDecimal x, BigDecimal y, int radius) {
        log.info("Searching store with itemId: {} and radius: {}", itemId, radius);

        List<StoreSearchDTO> stores = storeRepository.findStoresByItemIdAndRadius(itemId, x.doubleValue(), y.doubleValue(), radius).stream()
                .map(projection -> new StoreSearchDTO(
                        projection.getStoreNo(),
                        projection.getName(),
                        projection.getLocationX(),
                        projection.getLocationY(),
                        projection.getAddress(),
                        projection.getPhone(),
                        projection.getItemName(),
                        projection.getSalePrice(),
                        projection.getDiscountRate(),
                        projection.getQuantity(),
                        projection.getFlimarketYn(),
                        projection.getDistance()
                ))
                .toList();

        if (stores.isEmpty()) {
            log.warn("No stores found for itemId: {} and radius: {}", itemId, radius);
        }

        return stores;
    }

    /**
     * 매장 상세 정보 조회
     * 플리마켓 아이템은 공개하지 않음
     * - 매장 검색이 되었을 때 매장 번호 반환 
     * -> 해당 번호로 매장 접근
     * @param storeNo 매장 번호 
     * @return StoreDetailDTO
     */
    @Transactional(readOnly = true)
    public StoreDetailDTO getStoreDetail(int storeNo) {
        log.info("Getting store detail with storeNo: {}", storeNo);

        // 매장 조회
        StoreDTO store = storeRepository.findStoreDTOById(storeNo)
                .orElseThrow(() -> new StoreNotFoundException(storeNo));

        // 공지사항 조회
        List<AnnouncementDTO> announcements = announcementRepository.findAnnouncementsByStore(storeNo);

        // 판매 제품 조회
        List<StoreItemDTO> storeItems = storeItemRepository.findStoreItemsByStore(storeNo);

        return new StoreDetailDTO(store, announcements, storeItems);
    }

    /**
     * 공지사항 작성
     * @param announcementWriteDTO 등록할 공지사항 데이터
     */
    public void writeAnnouncement(AnnouncementWriteDTO announcementWriteDTO) {
        log.info("Writing announcement {}", announcementWriteDTO);

        // 매장 유효성 검사
        Store store = storeRepository.findByUser_Email(announcementWriteDTO.getUserEmail())
                .orElseThrow(() -> new StoreNotForbiddentException(announcementWriteDTO.getUserEmail()));

        // 공지사항 작성
        Announcement announcement = announcementWriteDTO.toEntity(store);
        log.debug("Converted announcementWriteDTO to Announcement entity: {}", announcement);

        saveAnnouncement(announcement);
    }

    /**
     * insert Announcement
     * @param announcement Announcement 객체
     * @return boardId가 추가된 Announcement 객체
     */
    private Announcement saveAnnouncement(Announcement announcement){
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        log.info("Announcement saved successfully with ID: {}", savedAnnouncement.getBoardId());
        return savedAnnouncement;
    }

    /**
     * 매장 공지 사항 수정
     * 수정은 로그인한 사장님이 -> storeNo 대신 user-email로
     * @param dto 수정 내용
     */
    @Transactional
    public void modifyAnnouncement(AnnouncementModifyDTO dto) {
        log.info("Modifying announcement {}", dto);

        // 게시글 유효성 검사
        Announcement announcement = announcementRepository.findByStore_User_Email(dto.getUserEmail());
        if(announcement == null){
            throw new BoardForbiddenException(dto.getUserEmail(), dto.getBoardId());
        }

        announcement.updateAnnouncement(dto);
        log.info("Announcement {} has been updated successfully", announcement);
    }

    /**
     * 공지 사항 삭제
     * 수정은 로그인한 사장님이 -> storeNo 대신 user-email로
     * @param dto 삭제할 공지 사항 정보
     */
    @Transactional
    public void deleteAnnouncement(AnnouncementDeleteDTO dto) {
        log.info("Deleting announcement {}", dto);

        // 게시글 유효성 검사
        Announcement announcement = announcementRepository.findByStore_User_EmailAndBoardId(dto.getUserEmail(), dto.getBoardId());
        if(announcement == null){
            throw new BoardForbiddenException(dto.getUserEmail(), dto.getBoardId());
        }

        announcementRepository.delete(announcement);
        log.info("Announcement {} has been deleted successfully", announcement);
    }


    /**
     * 플리마켓 상태 수정
     * - 플리마켓 비허용 -> 허용 : 플리마켓 사진, 섹션 개수 수정
     * - 플리마켓 허용 -> 비허용 : 사진, 개수 초기화
     * @param dto 플리마켓 정보
     */
    @Transactional
    public void modifyFlimarketState(FlimarketModifyDTO dto){
        log.info("Modifying flimarket state {}", dto);

        Store store = storeRepository.findByUser_Email(dto.getUserEmail())
                .orElseThrow(() -> new StoreNotForbiddentException(dto.getUserEmail()));

        store.modifyFlimarketState(dto);
        log.info("Flimarket state {} has been modified successfully", store);
    }

    @Transactional(readOnly = true)
    public Store findStoreByStoreNo(int storeNo) {
        return storeRepository.findById(storeNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 매장입니다."));
    }
}
