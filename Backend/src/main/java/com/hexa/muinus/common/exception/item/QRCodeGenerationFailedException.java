package com.hexa.muinus.common.exception.item;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class QRCodeGenerationFailedException extends MuinusException {
    public QRCodeGenerationFailedException() {
        super(ErrorCode.QR_CODE_GENERATION_FAILED);
    }
}