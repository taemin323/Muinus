package com.hexa.muinus.users.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateRequestDto {
    private final String userTelephone;
}
