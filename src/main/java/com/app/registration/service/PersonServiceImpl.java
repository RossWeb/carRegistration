package com.app.registration.service;

import com.app.registration.controller.request.AddressRequest;
import com.app.registration.controller.request.PersonRequest;
import com.app.registration.model.AddressEntity;
import com.app.registration.model.PersonEntity;
import com.app.registration.model.dto.AddressDto;
import com.app.registration.model.dto.PersonDto;
import com.app.registration.repository.AddressRepository;
import com.app.registration.repository.CriteriaFilter.PersonCriteriaFilter;
import com.app.registration.repository.PersonRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by konrad on 24.11.16.
 */
@Service
@Transactional(transactionManager = "platformTransactionManager", propagation = Propagation.REQUIRES_NEW)
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private AddressRepository addressRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public PersonDto createPerson(PersonRequest personRequest) {
        PersonDto personDto = new PersonDto();
        personDto.setPesel(personRepository.
                create(convertPersonRequestToEntity(personRequest)).getPesel());
        return personDto;
    }

    @Override
    public AddressDto createAddress(AddressRequest addressRequest) {
        AddressDto addressDto = new AddressDto();
        AddressEntity addressEntity = convertAddressRequestToEntity(addressRequest);
        addressEntity.setSigns(StringUtils.substring(addressEntity.getCity(), 0, 2).toUpperCase());
        addressDto.setId(addressRepository.
                create(addressEntity).
                getId());

        return addressDto;
    }

    @Override
    public AddressDto findAddressById(Long id){
        return convertAddressEntityToDto(addressRepository.findById(id));
    }

    @Override
    public List<PersonDto> searchPersons(PersonRequest personRequest) {
        return null;
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personRepository.findAll().stream().map(personEntity -> {
            PersonDto personDto = new PersonDto();
            personDto.setPesel(personEntity.getPesel());
            personDto.setName(personEntity.getName());
            personDto.setSurname(personEntity.getSurname());
            return personDto;
        }).collect(Collectors.toList());
    }

    @Override
    public PersonDto findByPesel(String pesel) {
        return convertPersonEntityToDto(personRepository.findByPesel(pesel));
    }

    @Override
    public void removePersonByPesel(String pesel) {
        PersonEntity personEntity = personRepository.findByPesel(pesel);
        personRepository.remove(personEntity);
    }

    @Override
    public String getSignPersonByPesel(String pesel) {
        return personRepository.findByPesel(pesel).getAddress().getSigns();
    }

    @Override
    public PersonDto updatePerson(PersonRequest personRequest) {
        PersonDto personDto = new PersonDto();
        personDto.setPesel(personRepository.update(convertPersonRequestToEntity(personRequest)).getPesel());
        return personDto;
    }

    @Override
    public AddressDto updateAddress(AddressRequest addressRequest) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressRepository.update(convertAddressRequestToEntity(addressRequest)).getId());
        return addressDto;
    }

    @Override
    public List<String> getSignsList() {
        return addressRepository.getSignsList();
    }

    private PersonEntity convertPersonRequestToEntity(PersonRequest personRequest){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(personRequest.getPersonDto().getName());
        personEntity.setSurname(personRequest.getPersonDto().getSurname());
        personEntity.setPhoneNumber(personRequest.getPersonDto().getPhoneNumber());
        personEntity.setPesel(personRequest.getPersonDto().getPesel());
        personEntity.setAddress(addressRepository.findById(personRequest.getPersonDto().getAddressId()));
        return personEntity;
    }

    private AddressEntity convertAddressRequestToEntity(AddressRequest addressRequest){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPostCode(addressRequest.getAddressDto().getPostCode());
        addressEntity.setStreet(addressRequest.getAddressDto().getStreet());
        addressEntity.setCity(addressRequest.getAddressDto().getCity());
        return addressEntity;
    }

    private AddressDto convertAddressEntityToDto(AddressEntity addressEntity){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressEntity.getId());
        addressDto.setStreet(addressEntity.getStreet());
        addressDto.setCity(addressEntity.getCity());
        addressDto.setPostCode(addressEntity.getPostCode());
        return addressDto;
    }

    private PersonDto convertPersonEntityToDto(PersonEntity personEntity){
        PersonDto personDto = new PersonDto();
        personDto.setPesel(personEntity.getPesel());
        personDto.setSurname(personEntity.getSurname());
        personDto.setName(personEntity.getName());
        personDto.setAddressId(personEntity.getAddress().getId());
        personDto.setPhoneNumber(personEntity.getPhoneNumber());
        return personDto;
    }
}
