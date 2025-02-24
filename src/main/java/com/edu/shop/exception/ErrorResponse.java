package com.edu.shop.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String code;
    private String message;
    private Integer status;
    private LocalDateTime timestamp;


    public ErrorResponse(String code, String message, Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /*public List<String> getInvalidParameters() {
        return invalidParameters;
    }

    public void setInvalidParameters(List<String> invalidParameters) {
        this.invalidParameters = invalidParameters;
    }*/
}
