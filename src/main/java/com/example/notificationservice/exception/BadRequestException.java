package com.example.notificationservice.exception;

import com.example.notificationservice.exception.base.BusinessException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BusinessException {

    public BadRequestException(String message, int errorCode) {

        super(message, errorCode);
    }

    public BadRequestException(String message, int errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
