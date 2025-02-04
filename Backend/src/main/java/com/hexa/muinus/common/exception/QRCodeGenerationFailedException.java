package com.hexa.muinus.common.exception;

public class QRCodeGenerationFailedException extends MuinusException {
    public QRCodeGenerationFailedException() {
        super(ErrorCode.QR_CODE_GENERATION_FAILED);
    }
}