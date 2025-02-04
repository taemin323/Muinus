package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class CouponAlreadyUsedException extends MuinusException {
    public CouponAlreadyUsedException() {
        super(ErrorCode.COUPON_ALREADY_USED);
    }
}