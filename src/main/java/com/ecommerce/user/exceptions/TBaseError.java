package com.ecommerce.user.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TBaseError {

    invalidUserToken("E001", "Token is invalid or expired", HttpStatus.UNAUTHORIZED),
    invalidCredentials("E002","Invalid login credentials",HttpStatus.FORBIDDEN),
    tokenCouldNotBeGenerated("E003", "Token generation failed for the user", HttpStatus.INTERNAL_SERVER_ERROR),
    nonUniqueEmailAddress("E004", "Email address must be unique", HttpStatus.BAD_REQUEST),
    userNotFound("E005", "User not found", HttpStatus.NOT_FOUND),
    oldPasswordNotMatched("E006", "Old password did not match with the records", HttpStatus.BAD_REQUEST),
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
