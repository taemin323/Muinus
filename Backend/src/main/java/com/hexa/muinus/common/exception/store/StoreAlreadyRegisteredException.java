package com.hexa.muinus.common.exception.store;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class StoreAlreadyRegisteredException extends MuinusException {
    public StoreAlreadyRegisteredException() {
        super(ErrorCode.STORE_ALREADY_REGISTERED);
    }
    public StoreAlreadyRegisteredException(String userEmail) {
        super(ErrorCode.STORE_ALREADY_REGISTERED, String.format("userEmail: %s", userEmail));
    }
}
