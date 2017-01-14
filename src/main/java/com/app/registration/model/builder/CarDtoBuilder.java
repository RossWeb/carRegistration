package com.app.registration.model.builder;

import com.app.registration.controller.request.CarRequest;
import com.app.registration.model.dto.CarDto;

import java.util.Date;

/**
 * Created by konrad on 14.01.17.
 */
public class CarDtoBuilder {

    private CarDto carDto = new CarDto();

    public CarDtoBuilder setCarCredentials(String vin, String name, Date productionDate){
        carDto.setVin(vin);
        carDto.setName(name);
        carDto.setProductionDate(productionDate);
        return this;
    }

    public CarDtoBuilder setOwner(String ownerPesel){
        carDto.setOwnerPesel(ownerPesel);
        return this;
    }

    public CarDto build(){
        return carDto;
    }

    public CarRequest decorateRequest(){
        CarRequest carRequest = new CarRequest();
        carRequest.setCarDto(carDto);
        return carRequest;
    }
}
