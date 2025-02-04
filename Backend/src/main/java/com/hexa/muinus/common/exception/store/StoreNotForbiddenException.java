package com.hexa.muinus.common.exception.store;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

public class StoreNotForbiddenException extends MuinusException {
    public StoreNotForbiddenException(Integer userNo, Integer storeNo) {
        super(ErrorCode.STORE_NOT_FORBIDDEN, "userNo: " + userNo + ", storeNo: " + storeNo);
    }

    public StoreNotForbiddenException(String email, Integer storeNo) {
        super(ErrorCode.STORE_NOT_FORBIDDEN, "email: " + email + ", storeNo: " + storeNo);
    }

    public StoreNotForbiddenException(String email) {
        super(ErrorCode.STORE_NOT_FORBIDDEN, "email: " + email);
    }
}
