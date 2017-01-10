package com.app.registration.repository;

import com.app.registration.model.AddressEntity;
import com.app.registration.model.DocumentEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.DocumentCriteriaFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface DocumentRepository extends GenericRepository<DocumentEntity> {

    public List<DocumentEntity> find(DocumentCriteriaFilter documentCriteriaFilter);

    public List<DocumentEntity> findAll();
}
