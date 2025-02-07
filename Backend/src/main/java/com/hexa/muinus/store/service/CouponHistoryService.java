package com.hexa.muinus.store.service;

import com.hexa.muinus.common.exception.coupon.CouponNotFoundException;
import com.hexa.muinus.store.domain.coupon.Coupon;
import com.hexa.muinus.store.domain.coupon.CouponHistory;
import com.hexa.muinus.store.domain.coupon.repository.CouponHistoryRepository;
import com.hexa.muinus.store.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponHistoryService {

    private final CouponHistoryRepository couponHistoryRepository;

    public CouponHistory findByStoreAndCoupon(Store store, Coupon coupon) {
        return couponHistoryRepository.findByStoreAndCoupon(store, coupon)
                .orElseThrow(() -> new CouponNotFoundException(coupon.getCouponId()));
    }
}
