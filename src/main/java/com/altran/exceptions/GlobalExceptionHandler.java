package com.altran.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseError> validationExceptionHandler(ValidationException validationException){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ResponseError.builder()
                .code(validationException.getCod())
                .message(validationException.getMessage())
                .build());
    }



}
