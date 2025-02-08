package com.hexa.muinus.common.exception.user;

import com.hexa.muinus.common.exception.APIErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class InvalidAccessTokenException extends MuinusException {
    public InvalidAccessTokenException() {
        super(APIErrorCode.INVALID_ACCESS_TOKEN);
    }
}