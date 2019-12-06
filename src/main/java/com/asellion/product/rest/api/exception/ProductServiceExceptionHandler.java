/*
 * Copyright (c) 2019, Asellion. All rights reserved.
 *
 */
package com.asellion.product.rest.api.exception;

import com.asellion.product.rest.api.bean.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/***
 * This {@code} ProductServiceExceptionHandler class responsible handling other exceptions such as Bad Request, Internal
 * Server Error, and Product Not Found.
 */


@RestControllerAdvice
public class ProductServiceExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles Product Not Found Exception
     * @param ex
     * @param request
     * @return ErrorDetails
     */

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleProductNotFoundException(
            ProductNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles Internal Server and Bad Request Exception
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails;
        if (ex instanceof NoHandlerFoundException) {
            errorDetails = new ErrorDetails(ex.getMessage(), LocalDateTime.now());
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }
        errorDetails = new ErrorDetails(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}