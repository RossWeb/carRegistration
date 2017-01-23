package com.app.registration.controller.response;

/**
 * Created by konrad on 21.11.16.
 */
public abstract class AbstractResponse {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
