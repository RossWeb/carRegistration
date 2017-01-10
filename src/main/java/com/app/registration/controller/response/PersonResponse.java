package com.app.registration.controller.response;

import com.app.registration.model.dto.PersonDto;

/**
 * Created by konrad on 24.11.16.
 */
public class PersonResponse extends AbstractResponse {

    private PersonDto personDto;

    public PersonDto getPersonDto() {
        return personDto;
    }

    public void setPersonDto(PersonDto personDto) {
        this.personDto = personDto;
    }
}
