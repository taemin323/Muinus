package com.hexa.muinus.common.exception.user;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;
import jakarta.validation.constraints.NotNull;

public class UserNotForbiddenException extends MuinusException {
    public UserNotForbiddenException(@NotNull Integer userNo) {
        super(ErrorCode.USER_NOT_FORBIDDEN, "userNo: " + userNo);
    }

    public UserNotForbiddenException(@NotNull String userEmail) {
        super(ErrorCode.USER_NOT_FORBIDDEN, "userEmail: " + userEmail);
    }
}