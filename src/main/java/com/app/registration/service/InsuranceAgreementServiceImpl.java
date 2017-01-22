package com.app.registration.service;

import com.app.registration.controller.request.InsuranceRequest;
import com.app.registration.model.InsuranceAgreementEntity;
import com.app.registration.model.PersonEntity;
import com.app.registration.model.dto.InsuranceAgreementDto;
import com.app.registration.repository.CarRepository;
import com.app.registration.repository.CriteriaFilter.InsuranceAgreementCriteriaFilter;
import com.app.registration.repository.InsuranceAgreementRepository;
import com.app.registration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by konrad on 01.01.17.
 */
@Service
@Transactional(transactionManager = "platformTransactionManager", propagation = Propagation.REQUIRES_NEW)
public class InsuranceAgreementServiceImpl implements InsuranceAgreementService {

    private InsuranceAgreementRepository insuranceAgreementRepository;
    private PersonRepository personRepository;
    private CarRepository carRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    public void setInsuranceAgreementRepository(InsuranceAgreementRepository insuranceAgreementRepository) {
        this.insuranceAgreementRepository = insuranceAgreementRepository;
    }

    @Override
    public InsuranceAgreementDto create(InsuranceRequest insuranceRequest) {
        return convertInsuranceEntityToDto(
                insuranceAgreementRepository.create(
                        convertInsuranceDtoToEntity(insuranceRequest.getInsuranceAgreementDto())));
    }

    @Override
    public List<InsuranceAgreementDto> search(InsuranceRequest insuranceRequest) {
        InsuranceAgreementCriteriaFilter insuranceAgreementCriteriaFilter = convertInsuranceDtoToFilter(insuranceRequest.getInsuranceAgreementDto());
        return insuranceAgreementRepository.find(insuranceAgreementCriteriaFilter).stream().map(this::convertInsuranceEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<InsuranceAgreementDto> getAll() {
        return insuranceAgreementRepository.findAll().stream().map(this::convertInsuranceEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteInsuranceByNumber(String insuranceNumber) {
        InsuranceAgreementEntity insuranceAgreementEntity = insuranceAgreementRepository.findByInsuranceNumber(insuranceNumber);
        insuranceAgreementRepository.remove(insuranceAgreementEntity);
    }

    @Override
    public InsuranceAgreementDto update(InsuranceRequest insuranceRequest) {
        InsuranceAgreementDto insuranceAgreementDto = new InsuranceAgreementDto();
        //todo dorobic find po id
        insuranceAgreementDto.setInsuranceNumber(insuranceAgreementRepository.update(convertInsuranceDtoToEntity(insuranceRequest.getInsuranceAgreementDto())).getInsuranceNumber());
        return insuranceAgreementDto;
    }

    private InsuranceAgreementCriteriaFilter convertInsuranceDtoToFilter(InsuranceAgreementDto insuranceAgreementDto){
        InsuranceAgreementCriteriaFilter insuranceAgreementCriteriaFilter = new InsuranceAgreementCriteriaFilter();
        if(insuranceAgreementDto.getBuyerPesel() != null){
            insuranceAgreementCriteriaFilter.setBuyer(personRepository.findByPesel(insuranceAgreementDto.getBuyerPesel()));

        }
        if(insuranceAgreementDto.getCarVin() != null){
            insuranceAgreementCriteriaFilter.setCar(carRepository.findByVin(insuranceAgreementDto.getCarVin()));
        }
        insuranceAgreementCriteriaFilter.setPurchaseDate(insuranceAgreementDto.getPurchaseDate());
        insuranceAgreementCriteriaFilter.setInsuranceNumber(insuranceAgreementDto.getInsuranceNumber());
        insuranceAgreementCriteriaFilter.setInsuranceCompanyName(insuranceAgreementDto.getInsuranceCompanyName());
        return insuranceAgreementCriteriaFilter;
    }

    private InsuranceAgreementEntity convertInsuranceDtoToEntity(InsuranceAgreementDto insuranceDto){
        InsuranceAgreementEntity insuranceAgreementEntity = new InsuranceAgreementEntity();
        insuranceAgreementEntity.setBuyer(personRepository.findByPesel(insuranceDto.getBuyerPesel()));
        insuranceAgreementEntity.setCar(carRepository.findByVin(insuranceDto.getCarVin()));
        insuranceAgreementEntity.setInsuranceCompanyName(insuranceDto.getInsuranceCompanyName());
        insuranceAgreementEntity.setInsuranceNumber(insuranceDto.getInsuranceNumber());
        insuranceAgreementEntity.setPurchaseDate(insuranceDto.getPurchaseDate());
        if(!insuranceDto.getOtherOwnerId().isEmpty()) {
            insuranceAgreementEntity.setOtherOwner(insuranceDto.getOtherOwnerId().stream().map(s -> personRepository.findByPesel(s)).collect(Collectors.toList()));
        }
        return insuranceAgreementEntity;
    }

    private InsuranceAgreementDto convertInsuranceEntityToDto(InsuranceAgreementEntity insuranceAgreementEntity){
        InsuranceAgreementDto insuranceAgreementDto = new InsuranceAgreementDto();
        if(insuranceAgreementEntity.getOtherOwner() != null) {
            insuranceAgreementDto.setOtherOwnerId(insuranceAgreementEntity.getOtherOwner().stream().map(PersonEntity::getPesel).collect(Collectors.toList()));
        }
        insuranceAgreementDto.setPurchaseDate(insuranceAgreementEntity.getPurchaseDate());
        insuranceAgreementDto.setInsuranceNumber(insuranceAgreementEntity.getInsuranceNumber());
        insuranceAgreementDto.setInsuranceCompanyName(insuranceAgreementEntity.getInsuranceCompanyName());
        insuranceAgreementDto.setBuyerPesel(insuranceAgreementEntity.getBuyer().getPesel());
        insuranceAgreementDto.setCarVin(insuranceAgreementEntity.getCar().getVin());
        return insuranceAgreementDto;
    }
}
