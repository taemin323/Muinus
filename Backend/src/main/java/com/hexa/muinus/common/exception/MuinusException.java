package com.hexa.muinus.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MuinusException extends RuntimeException {

    private final ErrorCode errorCode;
}