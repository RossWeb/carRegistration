package com.app.registration.repository;

import com.app.registration.model.AddressEntity;
import com.app.registration.model.InsuranceAgreementEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.InsuranceAgreementCriteriaFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface InsuranceAgreementRepository extends GenericRepository<InsuranceAgreementEntity> {

    List<InsuranceAgreementEntity> find(InsuranceAgreementCriteriaFilter insuranceAgreementCriteriaFilter);
    InsuranceAgreementEntity findByInsuranceNumber(String insuranceNumber);
    List<InsuranceAgreementEntity> findAll();
}
