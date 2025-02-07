package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class CouponAccessForbiddenException extends MuinusException {
    public CouponAccessForbiddenException() {
        super(ErrorCode.AVAILABLE_COUPON_NOT_FOUND);
    }
}
