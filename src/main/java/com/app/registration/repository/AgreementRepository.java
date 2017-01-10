package com.app.registration.repository;

import com.app.registration.model.AgreementEntity;
import com.app.registration.model.PersonEntity;
import com.app.registration.repository.CriteriaFilter.AgreementCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.PersonCriteriaFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface AgreementRepository extends GenericRepository<AgreementEntity> {

    public List<AgreementEntity> find(AgreementCriteriaFilter agreementCriteriaFilter);

    public List<AgreementEntity> findAll();
}
