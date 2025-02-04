package com.hexa.muinus.common.exception.general;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class NotFoundException extends MuinusException {
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }
}
