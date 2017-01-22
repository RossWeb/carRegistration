package com.app.registration.repository;

import com.app.registration.model.CarEntity;
import com.app.registration.repository.CriteriaFilter.CarCriteriaFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface CarRepository extends GenericRepository<CarEntity> {

    CarEntity findByVin(String vin);
    List<CarEntity> findAll();
    List<CarEntity> find(CarCriteriaFilter carCriteriaFilter);
}
