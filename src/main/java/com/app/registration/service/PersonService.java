package com.app.registration.service;

import com.app.registration.controller.request.AddressRequest;
import com.app.registration.controller.request.PersonRequest;
import com.app.registration.model.dto.AddressDto;
import com.app.registration.model.dto.PersonDto;

import java.util.List;
import java.util.Set;

/**
 * Created by konrad on 21.11.16.
 */
public interface PersonService{

    PersonDto createPerson(PersonRequest personRequest);
    AddressDto createAddress(AddressRequest addressRequest);
    AddressDto findAddressById(Long id);
    List<PersonDto> searchPersons(PersonRequest personRequest);
    List<PersonDto> getAllPersons();
    PersonDto findByPesel(String pesel);
    void removePersonByPesel(String pesel);
    String getCityPersonByPesel(String pesel);
    PersonDto updatePerson(PersonRequest personRequest);
    AddressDto updateAddress(AddressRequest addressRequest);
}
