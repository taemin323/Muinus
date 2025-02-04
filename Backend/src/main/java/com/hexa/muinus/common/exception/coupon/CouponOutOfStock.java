package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class CouponOutOfStock extends MuinusException {
    public CouponOutOfStock(int storeNo, int couponId) {
        super(ErrorCode.COUPON_OUT_OF_STOCK, "storeNo: " + storeNo + ", couponId: " + couponId);
    }
}