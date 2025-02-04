package com.hexa.muinus.common.exception.general;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class ForbiddenException extends MuinusException {
    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
