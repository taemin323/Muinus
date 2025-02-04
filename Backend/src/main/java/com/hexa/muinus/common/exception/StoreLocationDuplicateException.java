package com.hexa.muinus.common.exception;

import java.math.BigDecimal;

public class StoreLocationDuplicateException extends MuinusException {
    public StoreLocationDuplicateException(BigDecimal x, BigDecimal y) {
        super(ErrorCode.STORE_LOCATION_DUPLICATE, "locationX: " + x + ", locationY: " + y);
    }
}
