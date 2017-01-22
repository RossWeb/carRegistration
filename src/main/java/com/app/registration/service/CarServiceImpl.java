package com.app.registration.service;

import com.app.registration.controller.request.CarRequest;
import com.app.registration.controller.request.PersonRequest;
import com.app.registration.model.CarEntity;
import com.app.registration.model.PersonEntity;
import com.app.registration.model.dto.CarDto;
import com.app.registration.repository.CarRepository;
import com.app.registration.repository.CriteriaFilter.CarCriteriaFilter;
import com.app.registration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 2016-11-05.
 */
@Service
@Transactional(transactionManager = "platformTransactionManager", propagation = Propagation.REQUIRES_NEW)
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public CarDto createCar(CarRequest carRequest) {
        CarDto carDto = new CarDto();
        carDto.setVin(carRepository.create(convertCarRequestToEntity(carRequest)).getVin());
        return carDto;
    }

    @Override
    public CarDto findCarByVin(String vin) {
        return convertCarEntityToDto(carRepository.findByVin(vin));
    }

    @Override
    public void deleteCarByVin(String vin) {
        CarEntity carEntity = carRepository.findByVin(vin);
        carRepository.remove(carEntity);
    }

    @Override
    public List<CarDto> findAllCars() {
        return carRepository.findAll().stream().map(this::convertCarEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<CarDto> find(CarRequest carRequest) {
        return carRepository.find(convertDtoToFiler(carRequest.getCarDto()))
                .stream().map(this::convertCarEntityToDto).collect(Collectors.toList());
    }

    @Override
    public CarDto updateCar(CarRequest carRequest) {
        CarDto carDto = new CarDto();
        carDto.setVin(carRepository.update(convertCarRequestToEntity(carRequest)).getVin());
        return carDto;
    }

    private CarEntity convertCarRequestToEntity(CarRequest carRequest){
        CarEntity carEntity = new CarEntity();
        carEntity.setName(carRequest.getCarDto().getName());
        carEntity.setVin(carRequest.getCarDto().getVin());
        carEntity.setProductionDate(carRequest.getCarDto().getProductionDate());
        carEntity.setOwner(personRepository.findByPesel(carRequest.getCarDto().getOwnerPesel()));
        return carEntity;
    }

    private CarDto convertCarEntityToDto(CarEntity carEntity){
        CarDto carDto = new CarDto();
        carDto.setName(carEntity.getName());
        carDto.setVin(carEntity.getVin());
        carDto.setProductionDate(carEntity.getProductionDate());
        carDto.setOwnerPesel(carEntity.getOwner().getPesel());
        return carDto;
    }

    private CarCriteriaFilter convertDtoToFiler(CarDto dto) {
        CarCriteriaFilter carCriteriaFilter = new CarCriteriaFilter();
        carCriteriaFilter.setVin(dto.getVin());
        carCriteriaFilter.setName(dto.getName());
        carCriteriaFilter.setOwnerPesel(personRepository.findByPesel(dto.getOwnerPesel()));
        carCriteriaFilter.setProductionDate(dto.getProductionDate());
        return carCriteriaFilter;
    }
}

