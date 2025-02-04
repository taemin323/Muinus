package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

import java.time.LocalDateTime;

public class CouponExpiredException extends MuinusException {
    public CouponExpiredException(LocalDateTime expirationDate) {
        super(ErrorCode.COUPON_EXPIRED, "유효 기한 : " + expirationDate);
    }
}