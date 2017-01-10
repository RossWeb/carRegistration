package com.app.registration.controller.response;

import com.app.registration.model.dto.PersonDto;

import java.util.List;

/**
 * Created by konrad on 24.11.16.
 */
public class PersonsResponse extends AbstractResponse {

    private List<PersonDto> personsDto;

    public List<PersonDto> getPersonsDto() {
        return personsDto;
    }

    public void setPersonsDto(List<PersonDto> personsDto) {
        this.personsDto = personsDto;
    }
}
