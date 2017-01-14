package com.app.registration.model.builder;

import com.app.registration.controller.request.PersonRequest;
import com.app.registration.model.dto.PersonDto;

/**
 * Created by konrad on 14.01.17.
 */
public class PersonDtoBuilder {

    private PersonDto personDto = new PersonDto();

    public PersonDtoBuilder setPersonName(String name, String surname){
        this.personDto.setName(name);
        this.personDto.setSurname(surname);
        return this;
    }

    public PersonDtoBuilder setPersonPeselAndPhone(String pesel, String phone){
        this.personDto.setPesel(pesel);
        this.personDto.setPhoneNumber(phone);
        return this;
    }

    public PersonDtoBuilder setAddressIdAfterCreated(Long addressId){
        this.personDto.setAddressId(addressId);
        return this;
    }

    public PersonDto build(){
        return personDto;
    }

    public PersonRequest decorateRequest(){
        PersonRequest personRequest = new PersonRequest();
        personRequest.setPersonDto(personDto);
        return personRequest;
    }
}
