package com.app.registration.model.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by konrad on 04.01.17.
 */
public class ProofRegistrationDto {

    private String numberCardVehicle;
    private String plateNumber;
    private Date firstRegistrationDate;
    private String mainOwnerPesel;
    private List<String> otherOwnerPesels;
    private Date registrationDate;
    private Date registrationValidDate;
    private String carVin;
    private boolean temporaryProof;

    public String getNumberCardVehicle() {
        return numberCardVehicle;
    }

    public void setNumberCardVehicle(String numberCardVehicle) {
        this.numberCardVehicle = numberCardVehicle;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public void setFirstRegistrationDate(Date firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }

    public String getMainOwnerPesel() {
        return mainOwnerPesel;
    }

    public void setMainOwnerPesel(String mainOwnerPesel) {
        this.mainOwnerPesel = mainOwnerPesel;
    }

    public List<String> getOtherOwnerPesels() {
        return otherOwnerPesels;
    }

    public void setOtherOwnerPesels(List<String> otherOwnerPesels) {
        this.otherOwnerPesels = otherOwnerPesels;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationValidDate() {
        return registrationValidDate;
    }

    public void setRegistrationValidDate(Date registrationValidDate) {
        this.registrationValidDate = registrationValidDate;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

    public boolean isTemporaryProof() {
        return temporaryProof;
    }

    public void setTemporaryProof(boolean temporaryProof) {
        this.temporaryProof = temporaryProof;
    }
}
