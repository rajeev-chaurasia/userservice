package com.ecommerce.user.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class TBaseExceptionHandler {

    @ExceptionHandler(value = TBaseRuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleException(TBaseRuntimeException ex) {
        return TResponseEntityBuilder.errorResponseEntity(ex);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        return TResponseEntityBuilder.errorResponseEntity(ex);
    }
}
