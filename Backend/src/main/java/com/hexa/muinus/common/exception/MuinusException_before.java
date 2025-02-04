package com.hexa.muinus.common.exception;

public class MuinusException_before extends RuntimeException {
    private final int statusCode; // HTTP 상태 코드

    public MuinusException_before(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}