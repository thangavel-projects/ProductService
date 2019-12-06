/*
 * Copyright (c) 2019, Asellion. All rights reserved.
 *
 */
package com.asellion.product.rest.api.exception;

/***
 * This {@code} InvalidCredentialsException class responsible for throwing Invalid Credentials Exceptions
 */

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
