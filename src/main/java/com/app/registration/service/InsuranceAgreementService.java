package com.app.registration.service;

import com.app.registration.controller.request.InsuranceRequest;
import com.app.registration.model.dto.InsuranceAgreementDto;
import com.app.registration.repository.CriteriaFilter.InsuranceAgreementCriteriaFilter;

import java.util.List;

/**
 * Created by konrad on 01.01.17.
 */
public interface InsuranceAgreementService {

    InsuranceAgreementDto create(InsuranceRequest insuranceRequest);
    List<InsuranceAgreementDto> search(InsuranceRequest insuranceRequest);
    List<InsuranceAgreementDto> getAll();
    void deleteInsuranceByNumber(String insuranceNumber);
    InsuranceAgreementDto update(InsuranceRequest insuranceRequest);
}
