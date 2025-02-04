package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class AvailableCouponNotFoundException extends MuinusException {
    public AvailableCouponNotFoundException() {
        super(ErrorCode.AVAILABLE_COUPON_NOT_FOUND);
    }
}