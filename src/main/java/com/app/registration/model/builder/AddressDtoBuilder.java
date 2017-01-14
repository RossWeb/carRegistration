package com.app.registration.model.builder;

import com.app.registration.controller.request.AddressRequest;
import com.app.registration.model.dto.AddressDto;

/**
 * Created by konrad on 14.01.17.
 */
public class AddressDtoBuilder {

    private AddressDto addressDto = new AddressDto();

    public AddressDtoBuilder setAddress(String city, String postCode, String street){
        addressDto.setCity(city);
        addressDto.setStreet(street);
        addressDto.setPostCode(postCode);
        return this;
    }

    public AddressDto build(){
        return addressDto;
    }

    public AddressRequest decorateRequest(){
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressDto(addressDto);
        return addressRequest;
    }
}
