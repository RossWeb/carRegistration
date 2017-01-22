package com.app.registration.model.builder;

import com.app.registration.controller.request.PersonRequest;
import com.app.registration.model.dto.PersonDto;

/**
 * Created by konrad on 14.01.17.
 */
public class PersonDtoBuilder {

    private String pesel;
    private String name;
    private String surname;
    private String phoneNumber;
    private Long addressId;

    public PersonDtoBuilder setPersonName(String name, String surname){
        this.name = name;
        this.surname = surname;
        return this;
    }

    public PersonDtoBuilder setPersonPeselAndPhone(String pesel, String phone){
        this.pesel = pesel;
        this.phoneNumber = phone;
        return this;
    }

    public PersonDtoBuilder setAddressIdAfterCreated(Long addressId){
        this.addressId = addressId;
        return this;
    }

    public PersonDto build(){
        PersonDto personDto = new PersonDto();
        personDto.setAddressId(addressId);
        personDto.setName(name);
        personDto.setPhoneNumber(phoneNumber);
        personDto.setPesel(pesel);
        personDto.setSurname(surname);
        return personDto;
    }

    public PersonRequest decorateRequest(){
        PersonRequest personRequest = new PersonRequest();
        personRequest.setPersonDto(build());
        return personRequest;
    }
}
