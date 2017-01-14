package com.app.registration.repository;

import com.app.registration.model.PlateEntity;
import com.app.registration.repository.CriteriaFilter.PlateCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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
                Restrictions.like("signs",plateCriteriaFilter.getSign(), MatchMode.ANYWHERE),
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
    public List<Object[]> getUnusedSignCount(long minCountValue, int batchSize) {
        Query query = getSession().getNamedQuery(PlateEntity.GET_UNUSED_LESS_BY_COUNT);
        query.setParameter("minCountValue", minCountValue);
        query.setMaxResults(batchSize);
        return query.list();
    }

    @Override
    public List<PlateEntity> findAll() {
        Criteria plateCriteria = getSession().createCriteria(PlateEntity.class);
        return plateCriteria.list();
    }

    @Override
    public boolean isExistedBySign(String sign) {
        Criteria plateCriteria = getSession().createCriteria(PlateEntity.class);
        plateCriteria.add(Restrictions.eq("signs", sign));
        return !plateCriteria.list().isEmpty();
    }
}
