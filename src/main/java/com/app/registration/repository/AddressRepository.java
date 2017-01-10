package com.app.registration.repository;

import com.app.registration.model.AddressEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface AddressRepository extends GenericRepository<AddressEntity> {

    List<AddressEntity> find(AddressCriteriaFilter addressCriteriaFilter);

    AddressEntity findById(Long id);

    List<AddressEntity> findAll();

    List<String> getSignsList();
}
