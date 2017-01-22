package com.app.registration.repository;

import com.app.registration.model.CarEntity;
import com.app.registration.repository.CriteriaFilter.CarCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
@Repository
public class CarRepositoryImpl extends AbstractGenericRepositoryWithSession<CarEntity> implements CarRepository {

    @Override
    public CarEntity findByVin(String vin) {
        final Query query = getSession().getNamedQuery(CarEntity.FIND_BY_VIN);
        query.setParameter("vin", vin);
        return (CarEntity) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CarEntity> findAll() {
        final Query query = getSession().getNamedQuery(CarEntity.FIND_ALL);
        return query.list();
    }

    @Override
    public List<CarEntity> find(CarCriteriaFilter carCriteriaFilter) {
        Criteria carCriteria = getSession().createCriteria(CarEntity.class);
        carCriteria.add(Restrictions.disjunction(
                Restrictions.like("name",carCriteriaFilter.getName(), MatchMode.ANYWHERE),
                Restrictions.eq("owner",carCriteriaFilter.getOwnerPesel()),
                Restrictions.eq("productionDate",carCriteriaFilter.getProductionDate()),
                Restrictions.like("vin",carCriteriaFilter.getVin())));
        return (List<CarEntity>) carCriteria.list();
    }
}
