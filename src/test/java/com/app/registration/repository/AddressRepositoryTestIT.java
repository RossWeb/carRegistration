package com.app.registration.repository;

import com.app.registration.context.TestCorePersistenceContext;
import com.app.registration.model.AddressEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
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
@ContextConfiguration(classes = {TestCorePersistenceContext.class, AddressRepositoryImpl.class})
@Transactional("platformTransactionManager")
@Rollback
public class AddressRepositoryTestIT {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void createCarTest(){
        //given
        AddressEntity newAddress = createNewEntity("streettest", "citytest", "05-500");
        //when
        addressRepository.create(newAddress);
        //then
        Assert.assertNotNull(newAddress.getId());

    }

    @Test
    public void findCarTest(){
        //given
        AddressEntity newAddress1 = createNewEntity("streettest1", "citytest1", "05-500");
        AddressEntity newAddress2 = createNewEntity("streettest2", "citytest2", "05-500");
        addressRepository.create(newAddress1);
        addressRepository.create(newAddress2);
        AddressCriteriaFilter filter1 = new AddressCriteriaFilter();
        filter1.getAddressDto().setPostCode("05-500");
        AddressCriteriaFilter filter2 = new AddressCriteriaFilter();
        filter2.getAddressDto().setStreet("streettest2");
        filter2.getAddressDto().setCity("citytest2");
        //when
        List<AddressEntity> findAddressList1 = addressRepository.find(filter1);
        List<AddressEntity> findAddressList2 = addressRepository.find(filter2);
        //then
        Assert.assertNotNull(findAddressList1);
        Assert.assertTrue(findAddressList1.size() == 2);
        Assert.assertEquals("05-500", findAddressList1.get(0).getPostCode());
        Assert.assertNotNull(findAddressList2);
        Assert.assertTrue(findAddressList2.size() == 1);
        Assert.assertEquals("streettest2", findAddressList2.get(0).getStreet());
    }

    @Test
    public void findAllCarTest(){
        //given
        createCarTest();
        //when
        List<AddressEntity> addressEntityList = addressRepository.findAll();
        //then
        Assert.assertNotNull(addressEntityList);
        Assert.assertTrue(addressEntityList.size() > 0);
    }

    private AddressEntity createNewEntity(String street, String city, String postCode){
        AddressEntity newAddress = new AddressEntity();
        newAddress.setStreet(street);
        newAddress.setCity(city);
        newAddress.setPostCode(postCode);
        return newAddress;
    }
}
