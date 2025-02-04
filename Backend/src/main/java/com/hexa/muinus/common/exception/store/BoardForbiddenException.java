package com.hexa.muinus.common.exception.store;

import com.hexa.muinus.common.exception.ErrorCode;
import com.hexa.muinus.common.exception.MuinusException;

import java.math.BigDecimal;

public class BoardForbiddenException extends MuinusException {
    public BoardForbiddenException(String userEmail, Integer boardId) {
        super(ErrorCode.BOARD_FORBIDDEN, "userEmail: " + userEmail + ",  boardId" + boardId);
    }
}
