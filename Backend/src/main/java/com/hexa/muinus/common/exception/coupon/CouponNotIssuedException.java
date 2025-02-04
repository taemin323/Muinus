package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class CouponNotIssuedException extends MuinusException {
    public CouponNotIssuedException() {
        super(ErrorCode.COUPON_NOT_ISSUED);
    }

    public CouponNotIssuedException(int storeNo, int couponId) {
        super(ErrorCode.COUPON_NOT_ISSUED, String.format("storeNo: %d, couponId %d", storeNo, couponId));
    }
}
