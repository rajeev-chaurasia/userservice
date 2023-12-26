package com.ecommerce.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

public class TResponseEntityBuilder {
    public static final String SUCCESS = "success";
    public static final String STATUS = "status";
    public static final String DATA = "data";
    public static final String FAILED = "failed";
    public static final String ERROR_DETAILS = "errorDetails";
    public static final String DISPLAY_MESSAGE = "displayMessage";
    public static final String PARAMS = "params";
    public static final String ERROR_CODE = "errorCode";

    public static ResponseEntity<Map<String, Object>> okResponseEntity(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, SUCCESS);
        response.put(DATA, data);
        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Map<String, Object>> okCreatedResponseEntity(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, SUCCESS);
        response.put(DATA, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static ResponseEntity<Map<String, Object>> errorResponseEntity(Object errorDetails, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, FAILED);
        response.put(ERROR_DETAILS, errorDetails);
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    public static ResponseEntity<Map<String, Object>> errorResponseEntity(Throwable t) {
        if (t instanceof TBaseRuntimeException) {
            return errorResponseFromTBaseRuntimeException((TBaseRuntimeException) t);
        } else if (t instanceof MethodArgumentNotValidException) {
            return errorResponseFromValidationException((MethodArgumentNotValidException) t);
        }

        Map<String, Object> errorDetails = createErrorDetails(null, "unexpected.error", null);
        return errorResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Map<String, Object>> errorResponseFromTBaseRuntimeException(TBaseRuntimeException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, FAILED);

        Map<String, Object> details = createErrorDetails(
                exception.getErrorCode(),
                exception.getErrorMessage(),
                CollectionUtils.arrayToList(exception.getParams()));

        response.put(ERROR_DETAILS, details);

        return ResponseEntity
                .status(exception.getHttpStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    private static ResponseEntity<Map<String, Object>> errorResponseFromValidationException(MethodArgumentNotValidException exception) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, FAILED);

        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });


        Map<String, Object> details = createErrorDetails(
                null,
                "Validation failed",
                validationErrors);

        response.put(ERROR_DETAILS, details);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    public static Map<String, Object> createErrorDetails(String errorCode,String displayMessage, Object params) {
        Map<String, Object> details = new HashMap<>();
        safePut(details, ERROR_CODE, errorCode);
        safePut(details, DISPLAY_MESSAGE, displayMessage);
        safePut(details, PARAMS, params);
        return details;
    }

    private static void safePut(Map<String, Object> map, String key, Object value) {
        if (!StringUtils.hasLength(key)) {
            return;
        }
        if (value == null) {
            return;
        }
        map.put(key, value);
    }

}