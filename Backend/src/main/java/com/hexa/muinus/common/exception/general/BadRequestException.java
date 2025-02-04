package com.hexa.muinus.common.exception.general;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class BadRequestException extends MuinusException {
    public BadRequestException() {
        super(ErrorCode.BAD_REQUEST);
    }
}
