package com.hexa.muinus.common.exception;

import jakarta.validation.constraints.NotNull;

public class StoreNotFoundException extends MuinusException {
    public StoreNotFoundException(@NotNull Integer storeNo) {
        super(ErrorCode.STORE_NOT_FOUND, "storeNo: " + storeNo);
    }
}
