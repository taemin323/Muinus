package com.hexa.muinus.common.exception.general;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class GoneException extends MuinusException {
    public GoneException() {
        super(ErrorCode.GONE);
    }
}
