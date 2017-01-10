package com.app.registration.repository;

import com.app.registration.model.DocumentTypeEntity;
import com.app.registration.model.ProofRegistrationEntity;
import com.app.registration.repository.CriteriaFilter.DocumentTypeCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.ProofRegistrationFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by konrad on 21.11.16.
 */
@Repository
public class ProofRegistrationRepositoryImpl extends AbstractGenericRepositoryWithSession<ProofRegistrationEntity> implements ProofRegistrationRepository {

    @Override
    public List<ProofRegistrationEntity> find(ProofRegistrationFilter proofRegistrationFilter) {
        Criteria proofRegistrationCriteria = getSession().createCriteria(ProofRegistrationEntity.class);
        proofRegistrationCriteria.add(Restrictions.disjunction(
                Restrictions.eq("car",proofRegistrationFilter.getCar()),
                Restrictions.eq("isTemporaryProof",proofRegistrationFilter.isTemporaryProof()),
                Restrictions.eq("plateNumber",proofRegistrationFilter.getPlateNumber()),
                Restrictions.eq("mainOwner",proofRegistrationFilter.getMainOwner()),
                Restrictions.like("numberCardVehicle",proofRegistrationFilter.getNumberCardVehicle(), MatchMode.ANYWHERE),
                Restrictions.eq("registrationDate",proofRegistrationFilter.getRegistrationDate())));
        return proofRegistrationCriteria.list();
    }

    @Override
    public ProofRegistrationEntity findByNumberCardVehicle(String numberCardVehicle) {
        Criteria proofRegistrationCriteria = getSession().createCriteria(ProofRegistrationEntity.class);
        proofRegistrationCriteria.add(Restrictions.eq("numberCardVehicle", numberCardVehicle));
        return (ProofRegistrationEntity) proofRegistrationCriteria.uniqueResult();
    }

    @Override
    public List<ProofRegistrationEntity> findAll() {
        Criteria proofRegistrationCriteria = getSession().createCriteria(ProofRegistrationEntity.class);
        return proofRegistrationCriteria.list();
    }

    @Override
    public Long getTemporaryRegistrationsCount() {
        return getRegistrationsCountByTemporary(true);
    }

    @Override
    public Long getRegistrationsCount() {
        return getRegistrationsCountByTemporary(false);
    }

    private Long getRegistrationsCountByTemporary(boolean isTemporary){
        Criteria proofRegistrationCriteria = getSession().createCriteria(ProofRegistrationEntity.class);
        proofRegistrationCriteria.add(Restrictions.eq("temporaryProof", isTemporary));
        proofRegistrationCriteria.setProjection(Projections.rowCount());
        return  (Long) proofRegistrationCriteria.uniqueResult();
    }
}
