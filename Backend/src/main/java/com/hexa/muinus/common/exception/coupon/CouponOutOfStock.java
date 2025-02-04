package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class CouponOutOfStock extends MuinusException {
    public CouponOutOfStock() {
        super(ErrorCode.COUPON_OUT_OF_STOCK);
    }

    public CouponOutOfStock(int storeNo, int couponId) {
        super(ErrorCode.COUPON_OUT_OF_STOCK, String.format("storeNo: %d, couponId %d", storeNo, couponId));
    }
}