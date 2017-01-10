package com.app.registration.repository;

import com.app.registration.model.CarEntity;
import org.hibernate.Query;
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
}
