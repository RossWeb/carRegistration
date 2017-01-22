package com.app.registration.model.builder;

import com.app.registration.controller.request.InsuranceRequest;
import com.app.registration.model.dto.InsuranceAgreementDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by konrad on 14.01.17.
 */
public class InsuranceDtoBuilder {

    private String insuranceNumber;
    private Date purchaseDate;
    private String insuranceCompanyName;
    private String buyerPesel;
    private List<String> otherOwnerId = new ArrayList<>();
    private String carVin;

    public InsuranceDtoBuilder setInsuranceCredentials(String insuranceNumber, String companyName, Date purchaseDate){
        this.insuranceNumber = insuranceNumber;
        this.insuranceCompanyName = companyName;
        this.purchaseDate = purchaseDate;
        return this;

    }

    public InsuranceDtoBuilder setInsuranceOwner(String ownerPesel){
        this.buyerPesel = ownerPesel;
        return this;
    }

    public InsuranceDtoBuilder addInsuranceOtherOwner(String ownerId){
        this.otherOwnerId.add(ownerId);
        return this;
    }

    public InsuranceDtoBuilder setInsuranceCar(String carVin){
        this.carVin = carVin;
        return this;
    }


    public InsuranceAgreementDto build(){
        InsuranceAgreementDto insuranceAgreementDto = new InsuranceAgreementDto();
        insuranceAgreementDto.setInsuranceNumber(insuranceNumber);
        insuranceAgreementDto.setInsuranceCompanyName(insuranceCompanyName);
        insuranceAgreementDto.setPurchaseDate(purchaseDate);
        insuranceAgreementDto.setBuyerPesel(buyerPesel);
        insuranceAgreementDto.setCarVin(carVin);
        insuranceAgreementDto.setOtherOwnerId(otherOwnerId);
        return insuranceAgreementDto;
    }

    public InsuranceRequest decorateRequest(){
        InsuranceRequest insuranceRequest = new InsuranceRequest();
        insuranceRequest.setInsuranceAgreementDto(build());
        return insuranceRequest;
    }
}
