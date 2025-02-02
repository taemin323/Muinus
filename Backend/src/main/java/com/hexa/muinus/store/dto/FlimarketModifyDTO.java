package com.hexa.muinus.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlimarketModifyDTO {

    @Positive(message = "유효한 userNo를 입력해주세요.")
    private Integer userNo;

    @Positive(message = "유효한 storeNo를 입력해주세요.")
    private Integer storeNo;

    @NotBlank(message = "플리마켓 허용시 반드시 플리마켓 구역 사진이 필요합니다.")
    private String flimarketImageUrl;

    @Positive(message = "구역 개수를 입력해주세요.")
    private Integer flimarketSectionCnt;
}
