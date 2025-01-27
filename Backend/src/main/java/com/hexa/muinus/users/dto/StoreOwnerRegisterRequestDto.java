package com.hexa.muinus.users.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.geo.Point;

@Getter
@Builder
public class StoreOwnerRegisterRequestDto {

    private final String userName;
    private final String userEmail;
    private final String userTelephone;
    private final String userType;
    private final Integer userPoint;
    private final Point location;
    private final String storeName;
    private final String storeAddress;
    private final String registrationNumber;
    private final boolean isFliMarketAllowed;
}
