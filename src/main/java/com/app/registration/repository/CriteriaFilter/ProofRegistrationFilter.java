package com.app.registration.repository.CriteriaFilter;

import com.app.registration.model.CarEntity;
import com.app.registration.model.PersonEntity;
import com.app.registration.model.PlateEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by konrad on 12.11.16.
 */
public class ProofRegistrationFilter {

    private String numberCardVehicle;
    private PlateEntity plateNumber;
    private PersonEntity mainOwner;
    private Date registrationDate;
    private CarEntity car;
    private boolean isTemporaryProof;

    public String getNumberCardVehicle() {
        return numberCardVehicle;
    }

    public void setNumberCardVehicle(String numberCardVehicle) {
        this.numberCardVehicle = numberCardVehicle;
    }

    public PlateEntity getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(PlateEntity plateNumber) {
        this.plateNumber = plateNumber;
    }

    public PersonEntity getMainOwner() {
        return mainOwner;
    }

    public void setMainOwner(PersonEntity mainOwner) {
        this.mainOwner = mainOwner;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public boolean isTemporaryProof() {
        return isTemporaryProof;
    }

    public void setTemporaryProof(boolean temporaryProof) {
        isTemporaryProof = temporaryProof;
    }
}
