package com.ecommerce.user.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class TBaseRuntimeException extends RuntimeException {

    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;
    private String[] params;

    public TBaseRuntimeException(TBaseError error) {
        this.errorCode = error.getCode();
        this.errorMessage = error.getMessage();
        this.httpStatus = error.getHttpStatus();
    }

    public TBaseRuntimeException(TBaseError error, String... params) {
        this.errorCode = error.getCode();
        this.errorMessage = error.getMessage();
        this.httpStatus = error.getHttpStatus();
        this.params = params;
    }


}
