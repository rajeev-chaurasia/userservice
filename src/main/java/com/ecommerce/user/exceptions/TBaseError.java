package com.ecommerce.user.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TBaseError {

    invalidEmailAddress("E001", "Invalid email address", HttpStatus.BAD_REQUEST),
    userNotFound("E002", "No user exists for email :: {}", HttpStatus.NOT_FOUND)
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    TBaseError(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
