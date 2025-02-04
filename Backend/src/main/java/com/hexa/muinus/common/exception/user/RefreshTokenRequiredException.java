package com.hexa.muinus.common.exception.user;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class RefreshTokenRequiredException extends MuinusException {
    public RefreshTokenRequiredException() {
        super(ErrorCode.REFRESH_TOKEN_REQUIRED);
    }
}