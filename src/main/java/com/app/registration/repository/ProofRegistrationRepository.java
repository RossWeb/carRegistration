package com.app.registration.repository;

import com.app.registration.model.AddressEntity;
import com.app.registration.model.ProofRegistrationEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.ProofRegistrationFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface ProofRegistrationRepository extends GenericRepository<ProofRegistrationEntity> {

    List<ProofRegistrationEntity> find(ProofRegistrationFilter proofRegistrationFilter);
    ProofRegistrationEntity findByNumberCardVehicle(String numberCardVehicle);
    List<ProofRegistrationEntity> findAll();
    Long getTemporaryRegistrationsCount();
    Long getRegistrationsCount();
}
