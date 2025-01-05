package com.translator.dto;

import java.util.List;

public class ErrorResponse {
    private String message;
    private List<String> errors;
    private long timestamp;

    public ErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}