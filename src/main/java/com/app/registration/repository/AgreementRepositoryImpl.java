package com.app.registration.repository;

import com.app.registration.model.AgreementEntity;
import com.app.registration.model.PersonEntity;
import com.app.registration.repository.CriteriaFilter.AgreementCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.PersonCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by konrad on 12.11.16.
 */
@Repository
public class AgreementRepositoryImpl extends AbstractGenericRepositoryWithSession<AgreementEntity> implements AgreementRepository {

    @Override
    public List<AgreementEntity> find(AgreementCriteriaFilter agreementCriteriaFilter) {
        Criteria agreementCriteria = getSession().createCriteria(AgreementEntity.class);
        agreementCriteria.add(Restrictions.disjunction(
                Restrictions.like("city",agreementCriteriaFilter.getCity(), MatchMode.ANYWHERE),
                Restrictions.like("seller",agreementCriteriaFilter.getSeller()),
                Restrictions.like("buyer",agreementCriteriaFilter.getBuyer()),
                Restrictions.like("car",agreementCriteriaFilter.getCar())));
        return agreementCriteria.list();
    }

    @Override
    public List<AgreementEntity> findAll() {
        Criteria agreementCriteria = getSession().createCriteria(AgreementEntity.class);
        return agreementCriteria.list();
    }
}
