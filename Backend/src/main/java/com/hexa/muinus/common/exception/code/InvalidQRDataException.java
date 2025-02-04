package com.hexa.muinus.common.exception.code;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class InvalidQRDataException extends MuinusException {
    public InvalidQRDataException() {
        super(ErrorCode.INVALID_QR_DATA);
    }
}