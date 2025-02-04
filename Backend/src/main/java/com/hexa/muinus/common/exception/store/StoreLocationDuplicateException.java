package com.hexa.muinus.common.exception.store;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

import java.math.BigDecimal;

public class StoreLocationDuplicateException extends MuinusException {
    public StoreLocationDuplicateException() {
        super(ErrorCode.STORE_LOCATION_DUPLICATE);
    }
    public StoreLocationDuplicateException(BigDecimal x, BigDecimal y) {
        super(ErrorCode.STORE_LOCATION_DUPLICATE, String.format("locationX: %s, locationY: %s", x, y));
    }
}
