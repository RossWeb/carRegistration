package com.app.registration.repository;

import com.app.registration.model.AddressEntity;
import com.app.registration.model.DocumentTypeEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.DocumentTypeCriteriaFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface DocumentTypeRepository extends GenericRepository<DocumentTypeEntity> {

    public List<DocumentTypeEntity> find(DocumentTypeCriteriaFilter documentTypeCriteriaFilter);

    public List<DocumentTypeEntity> findAll();
}
