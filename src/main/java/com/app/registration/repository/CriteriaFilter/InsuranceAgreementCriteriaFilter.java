package com.app.registration.repository.CriteriaFilter;

import com.app.registration.model.CarEntity;
import com.app.registration.model.PersonEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by konrad on 12.11.16.
 */
public class InsuranceAgreementCriteriaFilter {

    private String insuranceNumber;
    private Date purchaseDate;
    private String insuranceCompanyName;
    private PersonEntity buyer;
    private CarEntity car;

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    public PersonEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(PersonEntity buyer) {
        this.buyer = buyer;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
}
