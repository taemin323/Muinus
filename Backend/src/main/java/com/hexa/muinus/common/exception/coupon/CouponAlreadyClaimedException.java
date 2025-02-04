package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class CouponAlreadyClaimedException extends MuinusException {
    public CouponAlreadyClaimedException() {
        super(ErrorCode.COUPON_ALREADY_CLAIMED);
    }
    public CouponAlreadyClaimedException(int storeNo, int couponId) {
        super(ErrorCode.COUPON_ALREADY_CLAIMED, String.format("storeNo: %d, couponId %d", storeNo, couponId));
    }
}