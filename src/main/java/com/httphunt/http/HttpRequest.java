package com.httphunt.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class HttpRequest {
    private URL url;

    private byte[] payload;

    private HttpMethod method;

    private String contentType;

    private Map<String, String> headers;

    private int connectionTimeOut;

    public HttpRequest(String url, HttpMethod method) throws MalformedURLException {
        this.setUrl(url);
        this.method = method;
    }

    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public URL getUrl() {
        return this.url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setPayload(String payload) {
        this.setPayload(payload == null ? null : payload.getBytes());
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public byte[] getPayload() {
        return payload;
    }

    public String getContentType() {
        return contentType;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {

        if (headers == null)
            headers = new HashMap<>();

        headers.put(key, value);
    }


    public String getHeaderValue(String key) {

        return this.headers == null ? null : this.headers.get(key);
    }
}
