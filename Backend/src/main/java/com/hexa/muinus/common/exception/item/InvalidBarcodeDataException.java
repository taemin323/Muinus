package com.hexa.muinus.common.exception.item;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class InvalidBarcodeDataException extends MuinusException {
    public InvalidBarcodeDataException() {
        super(ErrorCode.INVALID_BARCODE_DATA);
    }
}