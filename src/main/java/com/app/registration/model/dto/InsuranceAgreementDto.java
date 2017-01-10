package com.app.registration.model.dto;

import com.app.registration.model.CarEntity;
import com.app.registration.model.PersonEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by konrad on 01.01.17.
 */
public class InsuranceAgreementDto {

    private String insuranceNumber;
    private Date purchaseDate;
    private String insuranceCompanyName;
    private String buyerPesel;
    private List<String> otherOwnerId;
    private String carVin;

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

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

    public String getBuyerPesel() {
        return buyerPesel;
    }

    public void setBuyerPesel(String buyerPesel) {
        this.buyerPesel = buyerPesel;
    }

    public List<String> getOtherOwnerId() {
        return otherOwnerId;
    }

    public void setOtherOwnerId(List<String> otherOwnerId) {
        this.otherOwnerId = otherOwnerId;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }
}
