package com.hexa.muinus.common.exception.code;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class QRGenerationFailedException extends MuinusException {
    public QRGenerationFailedException() {
        super(ErrorCode.QR_GENERATION_FAILED);
    }
}