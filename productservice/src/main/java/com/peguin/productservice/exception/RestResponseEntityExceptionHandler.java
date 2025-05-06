package com.peguin.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.peguin.productservice.model.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleException(ProductServiceCustomException e) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(e.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
