package com.hexa.muinus.common.exception.user;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class UnregisteredUserException extends MuinusException {
    public UnregisteredUserException() {
        super(ErrorCode.UNREGISTERED_USER);
    }
}