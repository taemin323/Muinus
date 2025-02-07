package com.hexa.muinus.common.exception.user;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;
import jakarta.validation.constraints.NotNull;

public class UserEmailDuplicateException extends MuinusException {
    public UserEmailDuplicateException(@NotNull String userEmail) {
        super(ErrorCode.USER_EMAIL_DUPLICATE, "userEmail: " + userEmail);
    }
}