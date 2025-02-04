package com.hexa.muinus.users.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreOwnerUpdateRequestDto {
    private final String userEmail;
    private final String userTelephone;
    private final String storePhoneNumber;
}
