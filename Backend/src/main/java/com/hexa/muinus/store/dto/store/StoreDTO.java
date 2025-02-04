package com.hexa.muinus.store.dto.store;

import com.hexa.muinus.common.enums.YesNo;
import com.hexa.muinus.store.domain.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDTO {
    private Integer storeNo;
    private Integer userNo;
    private String name;
    private String address;
    private String storeImageUrl;
    private String phone;
    private YesNo flimarketYn;

    public static StoreDTO fromEntity(Store store) {
        return StoreDTO.builder()
                .storeNo(store.getStoreNo())
                .userNo(store.getUser().getUserNo())
                .name(store.getName())
                .address(store.getAddress())
                .storeImageUrl(store.getStoreImageUrl())
                .phone(store.getPhone())
                .flimarketYn(store.getFlimarketYn())
                .build();
    }

}
