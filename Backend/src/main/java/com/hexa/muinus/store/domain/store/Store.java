package com.hexa.muinus.store.domain.store;

import com.hexa.muinus.common.enums.YesNo;
import com.hexa.muinus.store.dto.store.FlimarketModifyDTO;
import com.hexa.muinus.store.dto.store.StoreModifyDTO;
import com.hexa.muinus.users.domain.user.Users;
import com.hexa.muinus.users.dto.StoreOwnerRegisterRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "store",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_location", columnNames = {"location_x", "location_y"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_no")
    private Integer storeNo;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Users user;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "location_x", nullable = false, precision = 10, scale = 7)
    private BigDecimal locationX;

    @Column(name = "location_y", nullable = false, precision = 10, scale = 7)
    private BigDecimal locationY;

    @Column(name = "address", nullable = false, length = 255, unique = true)
    private String address;

    @Column(name = "store_image_url", length = 255)
    private String storeImageUrl;

    @Column(name = "registration_no", nullable = false, unique = true, length = 255)
    private String registrationNo;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "flimarket_yn", nullable = false, columnDefinition = "ENUM('Y', 'N')")
    private YesNo flimarketYn = YesNo.N;

    @Column(name = "flimarket_image_url", length = 255)
    private String flimarketImageUrl;

    @Column(name = "flimarket_section_cnt", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer flimarketSectionCnt = 0;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "deleted", nullable = false, columnDefinition = "ENUM('Y', 'N')")
    private YesNo deleted = YesNo.N;

    public static Store create(Users user, StoreOwnerRegisterRequestDto requestDto) {
        return Store.builder()
                .user(user)
                .name(requestDto.getStoreName())
                .locationX(requestDto.getLocationX())
                .locationY(requestDto.getLocationY())
                .address(requestDto.getStoreAddress())
                .storeImageUrl(requestDto.getStoreImageUrl())
                .registrationNo(requestDto.getRegistrationNumber())
                .phone(requestDto.getPhone())
                .flimarketYn(requestDto.getIsFliMarketAllowed())
                .flimarketSectionCnt(requestDto.getFliMarketSectionCount())
                .deleted(YesNo.N)
                .build();
    }
    /**
     * 폐점처리 - `deleted` 컬럼값 "N"으로 변경
     */
    public void disableStore() {
        this.deleted = YesNo.Y;
    }

    /**
     * 매장 정보 수정
     * @param dto
     */
    public void updateStoreInfo(StoreModifyDTO dto){
        this.name = dto.getName();
        this.storeImageUrl = dto.getStoreImageUrl(); // null이면 기존 이미지 삭제, 있으면 변공
        this.phone = dto.getPhone();

        this.flimarketYn = dto.getFlimarketYn();

        if (this.flimarketYn == YesNo.Y) { // 플리마켓 허용
            this.flimarketImageUrl = dto.getFlimarketImageUrl();
            this.flimarketSectionCnt = dto.getFlimarketSectionCnt() != null ? dto.getFlimarketSectionCnt() : 0;
        } else { // 플리마켓이 비허용 필드 초기화
            this.flimarketImageUrl = null;
            this.flimarketSectionCnt = 0;
        }
    }

    public void modifyFlimarketState(FlimarketModifyDTO dto){
        this.flimarketYn = dto.getFlimarketYn();

        if (this.flimarketYn == YesNo.Y) { // 플리마켓 허용
            this.flimarketImageUrl = dto.getFlimarketImageUrl();
            this.flimarketSectionCnt = dto.getFlimarketSectionCnt() != null ? dto.getFlimarketSectionCnt() : 0;
        } else { // 플리마켓이 비허용 필드 초기화
            this.flimarketImageUrl = null;
            this.flimarketSectionCnt = 0;
        }
    }

}
