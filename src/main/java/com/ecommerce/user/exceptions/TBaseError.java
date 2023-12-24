package com.ecommerce.user.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TBaseError {

    invalidUserToken("E001", "Invalid login token", HttpStatus.UNAUTHORIZED),
    nonUniqueEmailAddress("E002", "Email address must be unique", HttpStatus.BAD_REQUEST),
    userNotFound("E003", "User not found", HttpStatus.NOT_FOUND),
    passwordNotMatched("E004", "Old password did not match with the records", HttpStatus.BAD_REQUEST),
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
