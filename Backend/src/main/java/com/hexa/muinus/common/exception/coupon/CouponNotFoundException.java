package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;
import jakarta.validation.constraints.NotNull;

public class CouponNotFoundException extends MuinusException {
    public CouponNotFoundException(@NotNull Integer couponId) {
        super(ErrorCode.COUPON_NOT_FOUND, "couponId: " + couponId);
    }
}
