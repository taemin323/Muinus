package com.hexa.muinus.common.exception.user;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class UnauthorizedException extends MuinusException {
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}
