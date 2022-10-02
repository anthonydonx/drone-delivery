/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.exception;

import com.musala.dronedelivery.exception.common.APIError;
import com.musala.dronedelivery.exception.common.DataValidationError;
import com.musala.dronedelivery.exception.common.UserInputError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * Global exception handler, Add or customize exception response
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String VALIDATION_ERROR = "Validation error";

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIError apiError=new APIError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(VALIDATION_ERROR);
        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, error, ex));
    }
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        APIError apiError = new APIError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        if(logger.isDebugEnabled()) apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(
            Exception ex) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        if(logger.isDebugEnabled()) apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(DataValidationError.class)
    protected ResponseEntity<Object> handleDataValidationError(
            DataValidationError ex) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST);
        apiError.addValidationErrors(ex.getResult().getFieldErrors());
        apiError.setMessage(ex.getMessage());
        if(logger.isDebugEnabled()) apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrity(
            DataIntegrityViolationException ex) {
        APIError apiError = new APIError(HttpStatus.NOT_ACCEPTABLE);
        apiError.setMessage(ex.getCause().getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(UserInputError.class)
    protected ResponseEntity<Object> handleUserInputError(
            UserInputError ex) {
        APIError apiError = new APIError(HttpStatus.NOT_ACCEPTABLE);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    protected ResponseEntity<Object> handleInvalidDataAccessApiUsageException(
            InvalidDataAccessApiUsageException ex) {
        APIError apiError = new APIError(HttpStatus.NOT_ACCEPTABLE);
        apiError.setMessage(ex.getCause().getMessage());
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
