package com.app.registration.repository;

import com.app.registration.controller.request.PlateRequest;
import com.app.registration.model.DocumentTypeEntity;
import com.app.registration.model.PlateEntity;
import com.app.registration.model.dto.PlateDto;
import com.app.registration.repository.CriteriaFilter.DocumentTypeCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.PlateCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by konrad on 21.11.16.
 */
@Repository
public class PlateRepositoryImpl extends AbstractGenericRepositoryWithSession<PlateEntity> implements PlateRepository {

    @Override
    public List<PlateEntity> find(PlateCriteriaFilter plateCriteriaFilter) {
        Criteria plateCriteria = getSession().createCriteria(PlateEntity.class);
        plateCriteria.add(Restrictions.disjunction(
                Restrictions.eq("used", plateCriteriaFilter.isUsed()),
                Restrictions.like("city",plateCriteriaFilter.getCity(), MatchMode.ANYWHERE),
                Restrictions.like("plateNumber",plateCriteriaFilter.getPlateNumber(), MatchMode.ANYWHERE)));
        plateCriteria.setMaxResults(plateCriteriaFilter.getMaxResults());
        return plateCriteria.list();
    }

    @Override
    public PlateEntity findByPlateNumber(String plateNumber) {
        Criteria plateCriteria = getSession().createCriteria(PlateEntity.class);
        plateCriteria.add(Restrictions.eq("plateNumber", plateNumber));
        return (PlateEntity) plateCriteria.uniqueResult();
    }

    @Override
    public Long getUnusedSignCount(String sign) {
        Criteria plateCriteria = getSession().createCriteria(PlateEntity.class);
        plateCriteria.add(Restrictions.eq("signs", sign));
        plateCriteria.setProjection(Projections.rowCount());
        return (Long) plateCriteria.uniqueResult();
    }

    @Override
    public List<PlateEntity> findAll() {
        Criteria plateCriteria = getSession().createCriteria(PlateEntity.class);
        return plateCriteria.list();
    }
}
