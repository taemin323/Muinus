package com.hexa.muinus.store.service;

import com.hexa.muinus.common.enums.YesNo;
import com.hexa.muinus.common.exception.StoreLocationDuplicateException;
import com.hexa.muinus.common.exception.StoreNotFoundException;
import com.hexa.muinus.common.exception.UserNotFoundException;
import com.hexa.muinus.store.domain.store.Store;
import com.hexa.muinus.store.dto.AnnouncementDTO;
import com.hexa.muinus.store.domain.information.respository.AnnouncementRepository;
import com.hexa.muinus.store.dto.FliItemDTO;
import com.hexa.muinus.store.dto.StoreItemDTO;
import com.hexa.muinus.store.domain.item.repository.FliItemRepository;
import com.hexa.muinus.store.domain.item.repository.StoreItemRespository;
import com.hexa.muinus.store.domain.store.repository.StoreRepository;
import com.hexa.muinus.store.dto.*;
import com.hexa.muinus.users.domain.user.Users;
import com.hexa.muinus.users.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final StoreItemRespository storeItemRespository;
    private final FliItemRepository fliItemRepository;


    /**
     * 매장 등록
     * @param storeRegisterDTO
     */
    public void registerStore(StoreRegisterDTO storeRegisterDTO) {
        log.info("Starting store registration for DTO: {}", storeRegisterDTO);

        // 사용자 유효성 검사
        Users user = userRepository.findById(storeRegisterDTO.getUserNo())
                .orElseThrow(() -> new UserNotFoundException(storeRegisterDTO.getUserNo()));


        // 주소 중복 확인
        if ((storeRepository.findByLocationXAndLocationY(storeRegisterDTO.getLocationX(), storeRegisterDTO.getLocationY())).isPresent()) {
            throw new StoreLocationDuplicateException(storeRegisterDTO.getLocationX(), storeRegisterDTO.getLocationY());
        }

        Store store = storeRegisterDTO.toEntity(user);
        log.debug("Converted StoreRegistDTO to Store entity: {}", store);

        save(store);
    }

    /**
     * Insert Store
     * 
     * @param store
     * @return storeNo 추가된 Store 객체
     */
    private Store save(Store store) {
        Store savedStore = storeRepository.save(store);
        log.info("Store saved successfully with ID: {}", savedStore.getStoreNo());
        return savedStore;
    }

    /**
     * 매장 삭제
     * - `deleted` 컬럼 값 "Y"-> "N" 으로 변경
     * - 해당 매장 비활성화
     * @param storeNo
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
     * @param storeModifyDTO
     */
    @Transactional
    public void modifyStore(StoreModifyDTO storeModifyDTO) {
        log.info("Modifying store with No: {}", storeModifyDTO.getStoreNo());

        // 사용자 조회
        Users user = userRepository.findById(storeModifyDTO.getUserNo())
                .orElseThrow(() -> new UserNotFoundException(storeModifyDTO.getUserNo()));

        // 사용자 - 매장 조회
        Store store = storeRepository.findByUserAndStoreNo(user, storeModifyDTO.getStoreNo())
                .orElseThrow(() -> new StoreNotFoundException(storeModifyDTO.getUserNo(), storeModifyDTO.getStoreNo()));

        store.updateStoreInfo(storeModifyDTO);
        log.info("Store {} has been updated successfully ({})", store.getStoreNo(), store);
    }

    /**
     * 제품으로 매장 조회 (단순 전체 조회 - 정렬되어 있지 않음)
     * @param itemId
     * @return
     */
    @Transactional(readOnly = true)
    public List<StoreSearchDTO> searchStore(int itemId) {
        log.info("Searching store with itemId: {}", itemId);

        List<StoreSearchDTO> stores = storeRepository.findStoresByItemId(itemId);

        if (stores.isEmpty()) {
            log.warn("No stores found for itemId: {}", itemId);
        }

        return stores;
    }

    /**
     * 매장 상세 정보 조회
     * @param storeNo
     * @return
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
        List<StoreItemDTO> storeItems = storeItemRespository.findStoreItemsByStore(storeNo);

        // 플리마켓 허용 시 플리마켓 제품 조회
        List<FliItemDTO> fliItems = store.getFlimarketYn() == YesNo.Y
                ? fliItemRepository.findSellingFliItemsByStore(storeNo)
                : new ArrayList<>();

        return new StoreDetailDTO(store, announcements, storeItems, fliItems);
    }
}
