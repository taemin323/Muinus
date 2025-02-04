package com.hexa.muinus.common.exception.store;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class StoreAlreadyRegisteredException extends MuinusException {
    public StoreAlreadyRegisteredException(String userEmail) {
        super(ErrorCode.STORE_ALREADY_REGISTERED, "userEmail: " + userEmail);
    }
}
