package com.mciefova.dbconnector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Entity not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Record not found")
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }
    
}
