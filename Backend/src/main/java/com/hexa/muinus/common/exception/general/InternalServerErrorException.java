package com.hexa.muinus.common.exception.general;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class InternalServerErrorException extends MuinusException {
    public InternalServerErrorException() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
