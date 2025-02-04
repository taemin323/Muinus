package com.hexa.muinus.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 400 Bad Request
    BAD_REQUEST(40000, "Bad Request"),
    INVALID_INPUT(40001, "유효하지 않은 값입니다."),
    USER_STORE_NOT_EXIST(40002, "일치하는 매장이 존재하지 않습니다."),
    PASSWORD_MISMATCH(40003, "비밀번호가 일치하지 않습니다."),
    INVALID_BARCODE_DATA(40004, "바코드 데이터가 유효하지 않습니다."),
    BARCODE_PARSING_ERROR(40005, "바코드 데이터를 파싱하는 중 오류가 발생했습니다."),

    // 404 Not Found
    NOT_FOUND(40400, "Not Found"),
    USER_NOT_FOUND(40401, "유효하지 않은 사용자입니다."),
    STORE_NOT_FOUND(40402, "유효하지 않은 매장입니다."),
    COUPON_NOT_FOUND(40403, "해당 쿠폰이 존재하지 않습니다."),
    COUPON_HISTORY_NOT_FOUND(40404, "쿠폰 히스토리가 존재하지 않습니다."),
    AVAILABLE_COUPON_NOT_FOUND(40405, "사용 가능한 쿠폰이 존재하지 않습니다."),

    // 409 Conflict
    CONFLICT(40900, "Conflict"),
    STORE_LOCATION_DUPLICATE(40901, "이미 등록된 위치입니다."),
    FAVORITE_STORE_DUPLICATE(40902, "해당 가게는 이미 즐겨찾기 목록에 있습니다."),
    COUPON_ALREADY_CLAIMED(40903, "이미 해당 쿠폰을 수령하였습니다."),

    // 410 Gone
    COUPON_EXPIRED(41001, "쿠폰의 유효 기간이 만료되었습니다."),
    COUPON_OUT_OF_STOCK(41002, "더 이상 발급 가능한 쿠폰이 없습니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(50000, "Internal server error"),
    QR_CODE_GENERATION_FAILED(50001, "QR코드 생성에 실패했습니다."),
    BARCODE_GENERATION_FAILED(50002, "바코드 생성에 실패했습니다."),

    ;

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
