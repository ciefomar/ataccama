package com.mciefova.dbconnector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Database type not supported exception.
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Database type not supported.")
public class DatabaseNotSupportedException extends RuntimeException {

    public DatabaseNotSupportedException() {
    }
    
}
