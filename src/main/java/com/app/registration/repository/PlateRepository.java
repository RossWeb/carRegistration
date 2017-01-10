package com.app.registration.repository;

import com.app.registration.controller.request.PlateRequest;
import com.app.registration.model.AddressEntity;
import com.app.registration.model.PlateEntity;
import com.app.registration.model.dto.PlateDto;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.PlateCriteriaFilter;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface PlateRepository extends GenericRepository<PlateEntity> {

    List<PlateEntity> find(PlateCriteriaFilter plateCriteriaFilter);

    PlateEntity findByPlateNumber(String plateNumber);

    List<PlateEntity> findAll();
}
