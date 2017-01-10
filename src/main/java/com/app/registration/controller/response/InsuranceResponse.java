package com.app.registration.controller.response;

import com.app.registration.model.dto.InsuranceAgreementDto;

/**
 * Created by konrad on 01.01.17.
 */
public class InsuranceResponse extends AbstractResponse {

    private InsuranceAgreementDto insuranceAgreement;

    public InsuranceAgreementDto getInsuranceAgreement() {
        return insuranceAgreement;
    }

    public void setInsuranceAgreement(InsuranceAgreementDto insuranceAgreement) {
        this.insuranceAgreement = insuranceAgreement;
    }
}
