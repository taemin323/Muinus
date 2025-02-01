package com.hexa.muinus.store.dto;

import com.hexa.muinus.common.enums.YesNo;
import com.hexa.muinus.common.validator.StoreFlimarketValidator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@StoreFlimarketValidator
public class StoreModifyDTO {

    @NotNull
    private Integer storeNo;

    @NotNull
    private Integer userNo;

    @NotNull
    private String name;

    private String storeImageUrl;

    @NotNull
    private String phone;

    @NotNull
    private YesNo flimarketYn;

    // flimarketYn : Y일 때 NotNull
    private String flimarketImageUrl;
    private Integer flimarketSectionCnt;

}
