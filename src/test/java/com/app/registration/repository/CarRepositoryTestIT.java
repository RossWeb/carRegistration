package com.app.registration.repository;

import com.app.registration.context.TestCorePersistenceContext;
import com.app.registration.model.CarEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2016-11-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCorePersistenceContext.class, CarRepositoryImpl.class})
@Transactional("platformTransactionManager")
@Rollback
public class CarRepositoryTestIT {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void createCarTest(){
        CarEntity newCar = new CarEntity();
        newCar.setName("testowy1");
        carRepository.create(newCar);
    }

    @Test
    public void findCarTest(){
        CarEntity newCar = new CarEntity();
        newCar.setName("testowy1");
        newCar = carRepository.create(newCar);
        CarEntity findCar = carRepository.findByVin(newCar.getVin());
        Assert.assertNotNull(findCar);
        Assert.assertTrue("Car name must be testowy1", "testowy1".equals(findCar.getName()));
    }



}
