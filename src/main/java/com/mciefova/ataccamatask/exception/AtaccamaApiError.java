package com.mciefova.ataccamatask.exception;

public class AtaccamaApiError {

    private String errorMessage;

    public AtaccamaApiError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
