package com.app.registration.repository;

import com.app.registration.context.TestCorePersistenceContext;
import com.app.registration.model.AddressEntity;
import com.app.registration.model.AgreementEntity;
import com.app.registration.model.CarEntity;
import com.app.registration.model.PersonEntity;
import com.app.registration.repository.CriteriaFilter.AgreementCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.PersonCriteriaFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 2016-11-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCorePersistenceContext.class, AddressRepositoryImpl.class,
        PersonRepositoryImpl.class, AgreementRepositoryImpl.class, CarRepositoryImpl.class})
@Transactional("platformTransactionManager")
@Rollback
public class AgreementRepositoryTestIT {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AgreementRepository agreementRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void createAgreementTest(){
        //given
        PersonEntity seller = createNewPerson("12345678901", "nametest1", "surnametest2", "222222222");
        PersonEntity buyer = createNewPerson("12345678902", "nametest1", "surnametest2", "333333333");
        personRepository.create(seller);
        personRepository.create(buyer);
        CarEntity car = new CarEntity();
        car.setName("Testcar");
        carRepository.create(car);
        AgreementEntity agreement = new AgreementEntity();
        agreement.setCity("CityTest");
        agreement.setBuyer(buyer);
        agreement.setSeller(seller);
        agreement.setPurchaseDate(new Date());
        agreement.setCarPrice(10000.00);
        agreement.setCar(car);
        //when
        agreementRepository.create(agreement);
        //then
        Assert.assertNotNull(agreement);
        Assert.assertNotNull(agreement.getId());
    }

    @Test
    public void findCarTest(){
        //given
        PersonEntity seller = createNewPerson("12345678901", "nametest1", "surnametest2", "222222222");
        PersonEntity buyer = createNewPerson("12345678902", "nametest1", "surnametest2", "333333333");
        personRepository.create(seller);
        personRepository.create(buyer);
        CarEntity car = new CarEntity();
        car.setName("Testcar");
        carRepository.create(car);
        AgreementEntity agreement = new AgreementEntity();
        agreement.setCity("CityTest");
        agreement.setBuyer(buyer);
        agreement.setSeller(seller);
        agreement.setPurchaseDate(new Date());
        agreement.setCarPrice(10000.00);
        agreement.setCar(car);
        agreementRepository.create(agreement);
        AgreementCriteriaFilter filter1 = new AgreementCriteriaFilter();
        filter1.setCity("CityTest");
        AgreementCriteriaFilter filter2 = new AgreementCriteriaFilter();
        filter2.setBuyer(buyer);
        //when
        List<AgreementEntity> findAgreementList1 = agreementRepository.find(filter1);
        List<AgreementEntity> findAgreementList2 = agreementRepository.find(filter2);
        //then
        Assert.assertNotNull(findAgreementList1);
        Assert.assertTrue(findAgreementList1.size() == 1);
        Assert.assertEquals("CityTest", findAgreementList1.get(0).getCity());
        Assert.assertEquals("12345678901", findAgreementList1.get(0).getSeller().getPesel());
        Assert.assertEquals("12345678902", findAgreementList1.get(0).getBuyer().getPesel());
        Assert.assertTrue(findAgreementList2.size() == 1);
        Assert.assertEquals("CityTest", findAgreementList2.get(0).getCity());
        Assert.assertEquals("12345678901", findAgreementList2.get(0).getSeller().getPesel());
        Assert.assertEquals("12345678902", findAgreementList2.get(0).getBuyer().getPesel());
    }

    @Test
    public void findAllCarTest(){
        //given
        createAgreementTest();
        //when
        List<AgreementEntity> agreementEntityList = agreementRepository.findAll();
        //then
        Assert.assertNotNull(agreementEntityList);
        Assert.assertTrue(agreementEntityList.size() > 0);
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
