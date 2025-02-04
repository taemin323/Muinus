package com.hexa.muinus.common.exception.coupon;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;
import com.hexa.muinus.store.domain.coupon.Coupon;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CouponAlreadyUsedException extends MuinusException {
    public CouponAlreadyUsedException() {
        super(ErrorCode.COUPON_ALREADY_USED);
    }

    public CouponAlreadyUsedException(int couponId) {
        super(ErrorCode.COUPON_ALREADY_USED, String.format("couponId: %d", couponId));
    }

    public CouponAlreadyUsedException(LocalDateTime usedDate) {
        super(ErrorCode.COUPON_ALREADY_USED, String.format("사용일자: %s", usedDate));
    }

    public CouponAlreadyUsedException(int couponId, int storeNo) {
        super(ErrorCode.COUPON_ALREADY_USED, String.format("couponId: %d, storeNo: %d", couponId, storeNo));
    }

    public CouponAlreadyUsedException(int couponId, LocalDateTime usedDate) {
        super(ErrorCode.COUPON_ALREADY_USED, String.format("couponId: %d, 사용일자: %s", couponId, usedDate));
    }

    public CouponAlreadyUsedException(int couponId, int storeNo, LocalDateTime usedDate) {
        super(ErrorCode.COUPON_ALREADY_USED, String.format("couponId: %d, storeNo: %s, 사용일자: %s", storeNo, couponId, usedDate));
    }
}