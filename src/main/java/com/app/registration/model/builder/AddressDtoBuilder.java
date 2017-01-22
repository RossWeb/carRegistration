package com.app.registration.model.builder;

import com.app.registration.controller.request.AddressRequest;
import com.app.registration.model.dto.AddressDto;

/**
 * Created by konrad on 14.01.17.
 */
public class AddressDtoBuilder {

    private String city;
    private String setPostCode;
    private String street;

    public AddressDtoBuilder setAddress(String city, String postCode, String street){
        this.setPostCode = postCode;
        this.street = street;
        this.city = city;
        return this;
    }

    public AddressDto build(){
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(city);
        addressDto.setPostCode(setPostCode);
        addressDto.setStreet(street);
        return addressDto;
    }

    public AddressRequest decorateRequest(){
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressDto(build());
        return addressRequest;
    }
}
