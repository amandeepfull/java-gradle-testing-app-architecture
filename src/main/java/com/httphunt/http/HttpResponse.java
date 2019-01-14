package com.httphunt.http;

import java.util.List;
import java.util.Map;


public class HttpResponse {

    private int statusCode;

    private String responseContent;

    private Map<String, List<String>> headers;

    public boolean wasSuccessful() {
        return (statusCode >= 200 && statusCode < 299);
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

}
