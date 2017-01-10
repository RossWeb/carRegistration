package com.app.registration.controller.request;

/**
 * Created by konrad on 04.01.17.
 */
public class PlateRequest extends AbstractRequest {

    private String city;
    private int count;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
