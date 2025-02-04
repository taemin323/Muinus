package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class CouponHistoryNotFoundException extends MuinusException {
    public CouponHistoryNotFoundException() {
        super(ErrorCode.COUPON_HISTORY_NOT_FOUND);
    }
}
