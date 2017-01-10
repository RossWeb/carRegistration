package com.app.registration.repository;

import com.app.registration.model.DocumentTypeEntity;
import com.app.registration.model.InsuranceAgreementEntity;
import com.app.registration.repository.CriteriaFilter.DocumentTypeCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.InsuranceAgreementCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by konrad on 21.11.16.
 */
@Repository
public class InsuranceAgreementRepositoryImpl extends AbstractGenericRepositoryWithSession<InsuranceAgreementEntity> implements InsuranceAgreementRepository {

    @Override
    public List<InsuranceAgreementEntity> find(InsuranceAgreementCriteriaFilter insuranceAgreementCriteriaFilter) {
        Criteria insuranceAgreementCriteria = getSession().createCriteria(InsuranceAgreementEntity.class);
        insuranceAgreementCriteria.add(Restrictions.disjunction(
                Restrictions.eq("buyer",insuranceAgreementCriteriaFilter.getBuyer()),
                Restrictions.eq("insuranceNumber",insuranceAgreementCriteriaFilter.getInsuranceNumber()),
                Restrictions.eq("car",insuranceAgreementCriteriaFilter.getCar()),
                Restrictions.like("insuranceCompanyName",insuranceAgreementCriteriaFilter.getInsuranceCompanyName(), MatchMode.ANYWHERE),
                Restrictions.eq("purchaseDate",insuranceAgreementCriteriaFilter.getPurchaseDate())));
        return insuranceAgreementCriteria.list();
    }

    @Override
    public InsuranceAgreementEntity findByInsuranceNumber(String insuranceNumber) {
        Criteria insuranceAgreementCriteria = getSession().createCriteria(InsuranceAgreementEntity.class);
        insuranceAgreementCriteria.add(Restrictions.eq("insuranceNumber", insuranceNumber));
        return (InsuranceAgreementEntity) insuranceAgreementCriteria.uniqueResult();
    }

    @Override
    public List<InsuranceAgreementEntity> findAll() {
        Criteria insuranceAgreementCriteria = getSession().createCriteria(InsuranceAgreementEntity.class);
        return insuranceAgreementCriteria.list();
    }
}
