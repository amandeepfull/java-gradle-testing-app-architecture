package com.httphunt.model.response;

public class HttpHuntApiResp {
    private String message;
    private int statusCode;

    public HttpHuntApiResp(){

    }

    public HttpHuntApiResp(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public HttpHuntApiResp(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
