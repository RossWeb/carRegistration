package com.app.registration.controller.response;

import com.app.registration.model.dto.CarDto;

import java.util.List;

/**
 * Created by konrad on 27.11.16.
 */
public class CarsResponse extends AbstractResponse {

    private List<CarDto> carsDto;

    public List<CarDto> getCarsDto() {
        return carsDto;
    }

    public void setCarsDto(List<CarDto> carsDto) {
        this.carsDto = carsDto;
    }
}
