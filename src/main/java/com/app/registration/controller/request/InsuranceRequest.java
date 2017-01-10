package com.app.registration.controller.request;

import com.app.registration.model.dto.InsuranceAgreementDto;

/**
 * Created by konrad on 01.01.17.
 */
public class InsuranceRequest extends AbstractRequest {

    private InsuranceAgreementDto insuranceAgreementDto;

    public InsuranceAgreementDto getInsuranceAgreementDto() {
        return insuranceAgreementDto;
    }

    public void setInsuranceAgreementDto(InsuranceAgreementDto insuranceAgreementDto) {
        this.insuranceAgreementDto = insuranceAgreementDto;
    }
}
