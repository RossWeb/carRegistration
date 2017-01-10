package com.app.registration.controller;

import com.app.registration.controller.request.AddressRequest;
import com.app.registration.controller.request.PersonRequest;
import com.app.registration.controller.response.AddressResponse;
import com.app.registration.controller.response.PersonResponse;
import com.app.registration.controller.response.PersonsResponse;
import com.app.registration.model.dto.PersonDto;
import com.app.registration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by konrad on 21.11.16.
 */
@Controller
@RequestMapping(value = "/person")
public class UserController {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public PersonResponse newPerson(@RequestBody PersonRequest person) {
        PersonResponse personResponse = new PersonResponse();
        PersonDto personDto = new PersonDto();
        personResponse.setPersonDto(personService.createPerson(person));
        return personResponse;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public PersonsResponse getPersons() {
        PersonsResponse personsResponse = new PersonsResponse();
        personsResponse.setPersonsDto(personService.getAllPersons());
        return personsResponse;
    }

    @RequestMapping(value = "/searchByPesel/{pesel}", method = RequestMethod.GET)
    @ResponseBody
    public PersonResponse searchPersonByPesel(@PathVariable(value="pesel") String pesel) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonDto(personService.findByPesel(pesel));
        return personResponse;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public PersonResponse editPerson(@RequestBody PersonRequest personRequest) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonDto(personService.updatePerson(personRequest));
        return personResponse;
    }

    @RequestMapping(value = "/delete/{pesel}", method = RequestMethod.GET)
    @ResponseBody
    public void deletePerson(@PathVariable(value="pesel") String pesel) {
        personService.removePersonByPesel(pesel);
    }

    @RequestMapping(value = "/address/new", method = RequestMethod.POST)
    @ResponseBody
    public AddressResponse newAddress(@RequestBody AddressRequest addressRequest) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setAddressDto(personService.createAddress(addressRequest));
        return addressResponse;
    }

    @RequestMapping(value = "address/searchById", method = RequestMethod.POST)
    @ResponseBody
    public AddressResponse searchAddress(@RequestParam Long id) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setAddressDto(personService.findAddressById(id));
        return addressResponse;
    }

    @RequestMapping(value = "address/edit", method = RequestMethod.POST)
    @ResponseBody
    public AddressResponse editAddress(@RequestBody AddressRequest addressRequest) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setAddressDto(personService.updateAddress(addressRequest));
        return addressResponse;
    }

    @RequestMapping(value = "address/delete", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deleteAddress() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }
}
