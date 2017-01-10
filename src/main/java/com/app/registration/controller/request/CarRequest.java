package com.app.registration.controller.request;

import com.app.registration.model.dto.CarDto;

/**
 * Created by konrad on 27.11.16.
 */
public class CarRequest extends AbstractRequest {

    private CarDto carDto;

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }
}
