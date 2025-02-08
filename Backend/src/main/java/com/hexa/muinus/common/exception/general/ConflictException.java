package com.hexa.muinus.common.exception.general;

import com.hexa.muinus.common.exception.APIErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class ConflictException extends MuinusException {
    public ConflictException() {
        super(APIErrorCode.CONFLICT);
    }
}
