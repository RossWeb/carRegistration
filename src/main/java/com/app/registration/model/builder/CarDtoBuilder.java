package com.app.registration.model.builder;

import com.app.registration.controller.request.CarRequest;
import com.app.registration.model.dto.CarDto;

import java.util.Date;

/**
 * Created by konrad on 14.01.17.
 */
public class CarDtoBuilder {

    private String name;
    private String vin;
    private Date productionDate;
    private String ownerPesel;


    public CarDtoBuilder setCarCredentials(String vin, String name, Date productionDate){
        this.vin = vin;
        this.name = name;
        this.productionDate = productionDate;
        return this;
    }

    public CarDtoBuilder setOwner(String ownerPesel){
        this.ownerPesel = ownerPesel;
        return this;
    }

    public CarDto build(){
        CarDto carDto = new CarDto();
        carDto.setOwnerPesel(ownerPesel);
        carDto.setVin(vin);
        carDto.setProductionDate(productionDate);
        carDto.setName(name);
        return carDto;
    }

    public CarRequest decorateRequest(){
        CarRequest carRequest = new CarRequest();
        carRequest.setCarDto(build());
        return carRequest;
    }
}
