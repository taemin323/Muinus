package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class CouponAlreadyClaimedException extends MuinusException {
    public CouponAlreadyClaimedException(int storeNo, int couponId) {
        super(ErrorCode.COUPON_ALREADY_CLAIMED, "storeNo: " + storeNo + ", couponId: " + couponId);
    }
}