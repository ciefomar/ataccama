package com.mciefova.ataccamatask;

import com.mciefova.ataccamatask.exception.ApiException;
import com.mciefova.ataccamatask.exception.AtaccamaApiError;
import com.mciefova.ataccamatask.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AtaccamaApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AtaccamaApiError handleNotFoundException() {
        return new AtaccamaApiError("Record not found");
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AtaccamaApiError handleConnectionFailureException(ApiException ex) {
        return new AtaccamaApiError(ex.getCause().getMessage());
    }

}
