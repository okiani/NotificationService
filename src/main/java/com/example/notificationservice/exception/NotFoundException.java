package com.example.notificationservice.exception;

import com.example.notificationservice.exception.base.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message, int errorCode) {

        super(message, errorCode);
    }

    public NotFoundException(String message, int errorCode, HttpStatus httpStatus) {
        super(message, errorCode, httpStatus);
    }
}
