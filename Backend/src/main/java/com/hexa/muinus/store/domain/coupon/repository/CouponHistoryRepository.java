package com.hexa.muinus.store.domain.coupon.repository;

import com.hexa.muinus.store.domain.coupon.CouponHistory;
import com.hexa.muinus.store.domain.coupon.CouponHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponHistoryRepository extends JpaRepository<CouponHistory, CouponHistoryId> {

    Optional<CouponHistory> findById(CouponHistoryId id);
}
