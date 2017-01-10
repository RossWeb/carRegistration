package com.app.registration.controller.response;

import com.app.registration.model.dto.InsuranceAgreementDto;

import java.util.List;

/**
 * Created by konrad on 01.01.17.
 */
public class InsurancesResponse extends AbstractResponse {

    List<InsuranceAgreementDto> insuranceAgreementList;

    public List<InsuranceAgreementDto> getInsuranceAgreementList() {
        return insuranceAgreementList;
    }

    public void setInsuranceAgreementList(List<InsuranceAgreementDto> insuranceAgreementList) {
        this.insuranceAgreementList = insuranceAgreementList;
    }
}
