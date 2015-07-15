package com.main.fw.json;

import org.springframework.stereotype.Component;

/**
 * PackALunch
 * Created by Sadra on 4/22/15.
 */
@Component
public class Response {

    private String status;
    private String message;

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public Response setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "status:'" + status + '\'' +
                ", message:'" + message + '\'' +
                '}';
    }
}
