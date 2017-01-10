package com.app.registration.controller.response;

import com.app.registration.model.dto.AddressDto;

/**
 * Created by konrad on 24.11.16.
 */
public class AddressResponse extends AbstractResponse {

    private AddressDto addressDto;

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
