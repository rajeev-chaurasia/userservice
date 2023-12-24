package com.ecommerce.user.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TBaseExceptionHandler {

    @ExceptionHandler(TBaseRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleTBaseRuntimeException(TBaseRuntimeException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ErrorResponse(ex.getErrorCode(), ex.getErrorMessage()));
    }
}
