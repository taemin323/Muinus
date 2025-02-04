package com.hexa.muinus.common.exception;

import lombok.Getter;

//  0 : 기본
// 10 : user
// 20 : store
// 30 : coupon
// 40 : item (+barcode/qr)

@Getter
public enum ErrorCode {
    // 400 Bad Request
    BAD_REQUEST(40000, "Bad Request"),
    INVALID_INPUT(40001, "유효하지 않은 값입니다."),
    INVALID_BARCODE_DATA(40040, "바코드 데이터가 유효하지 않습니다."),
    BARCODE_PARSING_ERROR(40041, "바코드 데이터를 파싱하는 중 오류가 발생했습니다."),

    // 401 Unauthorized
    UNAUTHORIZED(40100, "Unauthorized"),


    // 403 Forbidden
    FORBIDDEN(40300, "Forbidden"),
    USER_NOT_FORBIDDEN(40311, "접근이 허용되지 않은 사용자입니다."),
    STORE_NOT_FORBIDDEN(400321, "매장 정보에 접근할 권한이 없습니다."),
    BOARD_FORBIDDEN(40322, "공지사항을 수정할 권한이 없습니다."),

    // 404 Not Found
    NOT_FOUND(40400, "Not Found"),
    USER_NOT_FOUND(40410, "유효하지 않은 사용자입니다."),
    STORE_NOT_FOUND(40420, "유효하지 않은 매장입니다."),
    COUPON_NOT_FOUND(40430, "해당 쿠폰이 존재하지 않습니다."),
    COUPON_HISTORY_NOT_FOUND(40431, "쿠폰 히스토리가 존재하지 않습니다."),
    AVAILABLE_COUPON_NOT_FOUND(40432, "사용 가능한 쿠폰이 존재하지 않습니다."),

    // 409 Conflict
    CONFLICT(40900, "Conflict"),
    USER_DUPLICATE(40910, "이미 가입된 사용자입니다."),
    FAVORITE_STORE_DUPLICATE(40911, "해당 가게는 이미 즐겨찾기 목록에 있습니다."),
    STORE_LOCATION_DUPLICATE(40920, "이미 등록된 위치입니다."),
    COUPON_ALREADY_CLAIMED(40930, "이미 쿠폰을 수령하였습니다."),
    COUPON_ALREADY_USED(40931, "이미 사용된 쿠폰입니다."),


    // 410 Gone
    GONE(41000, "Gone"),
    COUPON_EXPIRED(41030, "쿠폰의 유효 기간이 만료되었습니다."),
    COUPON_OUT_OF_STOCK(41030, "더 이상 발급 가능한 쿠폰이 없습니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(50000, "Internal server error"),
    QR_CODE_GENERATION_FAILED(50040, "QR코드 생성에 실패했습니다."),
    BARCODE_GENERATION_FAILED(50041, "바코드 생성에 실패했습니다."),

    ;

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
