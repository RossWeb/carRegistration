package com.app.registration.repository.CriteriaFilter;

import com.app.registration.model.CarEntity;
import com.app.registration.model.PersonEntity;

/**
 * Created by konrad on 12.11.16.
 */
public class PlateCriteriaFilter {

    private String plateNumber;
    private CarEntity car;
    private String city;
    private boolean used;
    private int maxResults;

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
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
