package com.hexa.muinus.common.exception.store;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class StoreNotForbiddenException extends MuinusException {
    public StoreNotForbiddenException() {
        super(ErrorCode.STORE_NOT_FORBIDDEN);
    }
    public StoreNotForbiddenException(Integer userNo, Integer storeNo) {
        super(ErrorCode.STORE_NOT_FORBIDDEN, String.format("userNo: %d, storeNo: %d", userNo, storeNo));
    }

    public StoreNotForbiddenException(String email, Integer storeNo) {
        super(ErrorCode.STORE_NOT_FORBIDDEN, String.format("email: %s, storeNo: %d", email, storeNo));
    }

    public StoreNotForbiddenException(String email) {
        super(ErrorCode.STORE_NOT_FORBIDDEN, String.format("email: %s", email));
    }
}
