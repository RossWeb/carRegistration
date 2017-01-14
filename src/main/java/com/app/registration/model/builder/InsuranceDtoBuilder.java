package com.app.registration.model.builder;

import com.app.registration.controller.request.InsuranceRequest;
import com.app.registration.model.dto.InsuranceAgreementDto;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by konrad on 14.01.17.
 */
public class InsuranceDtoBuilder {

    private InsuranceAgreementDto insuranceAgreementDto = new InsuranceAgreementDto();

    public InsuranceDtoBuilder(){
        insuranceAgreementDto.setOtherOwnerId(new ArrayList<>());
    }

    public InsuranceDtoBuilder setInsuranceCredentials(String insuranceNumber, String companyName, Date purchaseDate){
        insuranceAgreementDto.setInsuranceNumber(insuranceNumber);
        insuranceAgreementDto.setInsuranceCompanyName(companyName);
        insuranceAgreementDto.setPurchaseDate(purchaseDate);
        return this;
    }

    public InsuranceDtoBuilder setInsuranceOwner(String ownerPesel){
        insuranceAgreementDto.setBuyerPesel(ownerPesel);
        return this;
    }

    public InsuranceDtoBuilder addInsuranceOtherOwner(String ownerId){
        insuranceAgreementDto.getOtherOwnerId().add(ownerId);
        return this;
    }

    public InsuranceDtoBuilder setInsuranceCar(String carVin){
        insuranceAgreementDto.setCarVin(carVin);
        return this;
    }

    public InsuranceAgreementDto build(){
        return insuranceAgreementDto;
    }

    public InsuranceRequest decorateRequest(){
        InsuranceRequest insuranceRequest = new InsuranceRequest();
        insuranceRequest.setInsuranceAgreementDto(insuranceAgreementDto);
        return insuranceRequest;
    }
}
