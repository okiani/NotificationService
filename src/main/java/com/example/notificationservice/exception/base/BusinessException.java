package com.example.notificationservice.exception.base;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final int errorCode;
    private final HttpStatus httpStatus;

    public BusinessException(String message, int errorCode) {
        super(message);

        this.errorCode = errorCode;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public BusinessException(String message, int errorCode, HttpStatus httpStatus) {
        super(message);

        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
