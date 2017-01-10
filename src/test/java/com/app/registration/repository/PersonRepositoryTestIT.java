package com.app.registration.repository;

import com.app.registration.context.TestCorePersistenceContext;
import com.app.registration.model.AddressEntity;
import com.app.registration.model.PersonEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.PersonCriteriaFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCorePersistenceContext.class, PersonRepositoryImpl.class, AddressRepositoryImpl.class})
@Transactional("platformTransactionManager")
@Rollback
public class PersonRepositoryTestIT {

    private final static String PESEL = "12345678901";
    private final static String PHONE = "999999999";
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void createPersonTest(){
        //given
        PersonEntity newPerson = createNewPerson(PESEL, "nametest1", "surnametest2", PHONE);
        //when
        PersonEntity personCommit = personRepository.create(newPerson);
        //then
        Assert.assertNotNull(personCommit);
        Assert.assertNotNull(personCommit.getAddress());
    }

    @Test
    public void findCarTest(){
        //given
        PersonEntity newPerson1 = createNewPerson(PESEL, "nametest1", "surnametest2", PHONE);
        personRepository.create(newPerson1);
        PersonCriteriaFilter filter1 = new PersonCriteriaFilter();
        filter1.getPersonDto().setPesel(PESEL);
        //when
        List<PersonEntity> findPersonList1 = personRepository.find(filter1);
        //then
        Assert.assertNotNull(findPersonList1);
        Assert.assertTrue(findPersonList1.size() == 1);
        Assert.assertEquals(PHONE, findPersonList1.get(0).getPhoneNumber());
    }

    @Test
    public void findAllCarTest(){
        //given
        createPersonTest();
        //when
        List<PersonEntity> personEntityList = personRepository.findAll();
        //then
        Assert.assertNotNull(personEntityList);
        Assert.assertTrue(personEntityList.size() > 0);
    }

    private PersonEntity createNewPerson(String pesel, String name, String surname, String phoneNumber){
        PersonEntity newPerson = new PersonEntity();
        newPerson.setName(name);
        newPerson.setPesel(pesel);
        newPerson.setPhoneNumber(phoneNumber);
        newPerson.setSurname(surname);
        newPerson.setAddress(createNewAddress("streettest1", "citytest1", "05-500"));
        return newPerson;
    }

    private AddressEntity createNewAddress(String street, String city, String postCode){
        AddressEntity newAddress = new AddressEntity();
        newAddress.setStreet(street);
        newAddress.setCity(city);
        newAddress.setPostCode(postCode);
        addressRepository.create(newAddress);
        return newAddress;
    }

}
