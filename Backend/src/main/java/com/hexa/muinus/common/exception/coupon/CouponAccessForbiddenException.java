package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.APIErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class CouponAccessForbiddenException extends MuinusException {
    public CouponAccessForbiddenException() {
        super(APIErrorCode.AVAILABLE_COUPON_NOT_FOUND);
    }
}
