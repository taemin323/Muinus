package com.hexa.muinus.common.exception.user;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;
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