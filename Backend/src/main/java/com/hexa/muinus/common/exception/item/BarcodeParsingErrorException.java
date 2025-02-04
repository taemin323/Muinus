package com.hexa.muinus.common.exception.item;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class BarcodeParsingErrorException extends MuinusException {
    public BarcodeParsingErrorException() {
        super(ErrorCode.BARCODE_PARSING_ERROR);
    }
}