package com.app.registration.model.dto;

/**
 * Created by konrad on 04.01.17.
 */
public class PlateDto {

    private String plateNumber;
    private String signs;
    private boolean used;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getSigns() {
        return signs;
    }

    public void setSigns(String signs) {
        this.signs = signs;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
