package com.archobyte.SimpleMcRecords.Web;

public class WebApiResponse {
    int statusCode;
    String body;

    public WebApiResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "statusCode: " + Integer.toString(getStatusCode()) + " body: " + getBody();
    }
}
