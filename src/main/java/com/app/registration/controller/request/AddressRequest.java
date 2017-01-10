package com.app.registration.controller.request;

import com.app.registration.model.dto.AddressDto;

/**
 * Created by konrad on 24.11.16.
 */
public class AddressRequest extends AbstractRequest {

    private AddressDto addressDto;

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
