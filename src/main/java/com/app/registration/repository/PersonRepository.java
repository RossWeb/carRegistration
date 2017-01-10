package com.app.registration.repository;

import com.app.registration.model.PersonEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.PersonCriteriaFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface PersonRepository extends GenericRepository<PersonEntity> {

    List<PersonEntity> find(PersonCriteriaFilter personCriteriaFilter);
    List<PersonEntity> findAll();
    PersonEntity findByPesel(String pesel);
}
