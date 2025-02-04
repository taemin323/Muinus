package com.hexa.muinus.common.exception.code;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class QRParsingErrorException extends MuinusException {
    public QRParsingErrorException() {
        super(ErrorCode.QR_PARSING_ERROR);
    }
}