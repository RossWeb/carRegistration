package com.app.registration.repository;

import com.app.registration.model.DocumentTypeEntity;
import com.app.registration.model.InvoiceEntity;
import com.app.registration.repository.CriteriaFilter.DocumentTypeCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.InvoiceCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by konrad on 21.11.16.
 */
public class InvoiceRepositoryImpl extends AbstractGenericRepositoryWithSession<InvoiceEntity> implements InvoiceRepository {

    @Override
    public List<InvoiceEntity> find(InvoiceCriteriaFilter invoiceCriteriaFilter) {
        Criteria invoiceCriteria = getSession().createCriteria(InvoiceEntity.class);
        invoiceCriteria.add(Restrictions.disjunction(
                Restrictions.eq("buyer",invoiceCriteriaFilter.getBuyer()),
                Restrictions.eq("car",invoiceCriteriaFilter.getCar()),
                Restrictions.like("shopName",invoiceCriteriaFilter.getShopName(), MatchMode.ANYWHERE)));
        return invoiceCriteria.list();
    }

    @Override
    public List<InvoiceEntity> findAll() {
        Criteria invoiceCriteria = getSession().createCriteria(InvoiceEntity.class);
        return invoiceCriteria.list();
    }
}
