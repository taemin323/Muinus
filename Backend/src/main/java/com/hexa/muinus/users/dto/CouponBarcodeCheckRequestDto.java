package com.hexa.muinus.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponBarcodeCheckRequestDto {
    private String barcodeData;// 바코드에 포함된 데이터
}
