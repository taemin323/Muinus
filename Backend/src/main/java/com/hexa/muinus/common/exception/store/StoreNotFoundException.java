package com.hexa.muinus.common.exception.store;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;
import jakarta.validation.constraints.NotNull;

public class StoreNotFoundException extends MuinusException {
    public StoreNotFoundException() {
        super(ErrorCode.STORE_NOT_FOUND);
    }
    public StoreNotFoundException(@NotNull Integer storeNo) {
        super(ErrorCode.STORE_NOT_FOUND, String.format("storeNo: %d", storeNo));
    }
}
