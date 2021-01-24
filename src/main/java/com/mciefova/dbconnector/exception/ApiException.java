package com.mciefova.dbconnector.exception;

/**
 * Main API exception.
 */
public class ApiException extends RuntimeException {

    public ApiException (Throwable cause) {
        super(cause);
    }
}
