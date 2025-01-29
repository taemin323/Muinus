package com.hexa.muinus.store.domain.dto;

import com.hexa.muinus.store.domain.Store;
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
    private Store.YesNo flimarketYn;
}
