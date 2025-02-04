package com.hexa.muinus.common.exception;

import jakarta.validation.constraints.NotNull;

public class UserNotFoundException extends MuinusException {
    public UserNotFoundException(@NotNull Integer userNo) {
        super(ErrorCode.USER_NOT_FOUND, "userNo: " + userNo);
    }

    public UserNotFoundException(@NotNull String userEmail) {
        super(ErrorCode.USER_NOT_FOUND, "userEmail: " + userEmail);
    }

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}