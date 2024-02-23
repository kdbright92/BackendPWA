package com.example.BackendPWA.ModelResp;

import org.springframework.http.HttpStatus;

public class ErrorResp {
    HttpStatus httpStatus;
    String message;

    public ErrorResp(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
