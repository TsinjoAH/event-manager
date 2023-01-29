package com.management.events.exceptions;

public class FormattedError {
    private String message;

    public FormattedError(String message) {
        setMessage(message);
    }

    public FormattedError(Exception e) {
        setMessage(e.getMessage());
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
