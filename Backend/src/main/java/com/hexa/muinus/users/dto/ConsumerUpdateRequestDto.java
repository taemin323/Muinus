package com.hexa.muinus.users.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConsumerUpdateRequestDto {
    private final String userEmail;
    private final String userTelephone;
}
