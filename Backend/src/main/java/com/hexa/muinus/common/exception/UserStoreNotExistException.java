package com.hexa.muinus.common.exception;

public class UserStoreNotExistException extends MuinusException{
    public UserStoreNotExistException(Integer userNo, Integer storeNo) {
        super(ErrorCode.USER_STORE_NOT_EXIST, "userNo: " + userNo + ", storeNo: " + storeNo);
    }
}
