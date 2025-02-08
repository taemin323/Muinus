package com.hexa.muinus.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PreferenceId {
    private Long userNo;
    private Long itemId;
}
