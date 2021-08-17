package com.example.notificationservice.exception.base;

import com.example.notificationservice.transformer.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> NotFoundException(BusinessException e, WebRequest request) {

        return ResponseHandler.generateResponse(e.getMessage(), 404, HttpStatus.NOT_FOUND, null);
    }
}
