package com.app.registration.service;

import com.app.registration.controller.request.CarRequest;
import com.app.registration.model.CarEntity;
import com.app.registration.model.dto.CarDto;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
public interface CarService {

    CarDto createCar(CarRequest carRequest);
    CarDto findCarByVin(String vin);
    void deleteCarByVin(String vin);
    List<CarDto> findAllCars();
    CarDto updateCar(CarRequest carRequest);
}
