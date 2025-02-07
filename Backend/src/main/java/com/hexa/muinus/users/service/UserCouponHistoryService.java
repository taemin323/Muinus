package com.hexa.muinus.users.service;

import com.hexa.muinus.users.domain.coupon.UserCouponHistory;
import com.hexa.muinus.users.domain.coupon.repository.UserCouponHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCouponHistoryService {

    private final UserCouponHistoryRepository userCouponHistoryRepository;

    public void save(UserCouponHistory userCouponHistory) {
        userCouponHistoryRepository.save(userCouponHistory);
    }
}
