package com.hexa.muinus.common.exception;

import org.springframework.http.HttpStatus;

public class StoreLocationDuplicateException extends MuinusException {
    public StoreLocationDuplicateException(double x, double y) {
        super("이미 등록된 위치입니다. locationX:" + x + ", locationY:" + y, 400);
    }
}