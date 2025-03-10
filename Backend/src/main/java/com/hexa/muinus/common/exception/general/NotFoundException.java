package com.hexa.muinus.common.exception.general;

import com.hexa.muinus.common.exception.APIErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class NotFoundException extends MuinusException {
    public NotFoundException() {
        super(APIErrorCode.NOT_FOUND);
    }
}
