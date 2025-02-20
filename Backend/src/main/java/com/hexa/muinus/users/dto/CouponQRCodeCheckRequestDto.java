package com.hexa.muinus.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponQRCodeCheckRequestDto {
    private String qrData;// 바코드에 포함된 데이터
}
