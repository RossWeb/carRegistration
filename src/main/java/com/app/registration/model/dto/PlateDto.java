package com.app.registration.model.dto;

/**
 * Created by konrad on 04.01.17.
 */
public class PlateDto {

    private String plateNumber;
    private String city;
    private boolean used;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
