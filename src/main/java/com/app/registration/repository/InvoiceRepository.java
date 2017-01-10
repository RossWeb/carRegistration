package com.app.registration.repository;

import com.app.registration.model.AddressEntity;
import com.app.registration.model.InvoiceEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.InvoiceCriteriaFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface InvoiceRepository extends GenericRepository<InvoiceEntity> {

    public List<InvoiceEntity> find(InvoiceCriteriaFilter invoiceCriteriaFilter);

    public List<InvoiceEntity> findAll();
}
