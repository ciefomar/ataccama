package com.mciefova.ataccamatask.exception;

public class AtaccamaApiError {

    private String message;

    public AtaccamaApiError(String errorMessage) {
        this.message = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
