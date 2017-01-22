package com.app.registration.service;

import com.app.registration.controller.request.ProofRegistrationRequest;
import com.app.registration.model.PersonEntity;
import com.app.registration.model.ProofRegistrationEntity;
import com.app.registration.model.dto.ProofRegistrationDto;
import com.app.registration.repository.CarRepository;
import com.app.registration.repository.PersonRepository;
import com.app.registration.repository.PlateRepository;
import com.app.registration.repository.ProofRegistrationRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by konrad on 02.01.17.
 */
@Service
@Transactional(transactionManager = "platformTransactionManager", propagation = Propagation.REQUIRES_NEW)
public class ProofRegistrationServiceImpl implements ProofRegistrationService {

    private RegistrationNumberService registrationNumberService;
    private ProofRegistrationRepository proofRegistrationRepository;
    private CarRepository carRepository;
    private PersonRepository personRepository;
    private PlateRepository plateRepository;

    private final Long VALID_TIME = 24L;
    private final Long TEMP_VALID_TIME = 3L;

    @Autowired
    public void setPlateRepository(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    public void setProofRegistrationRepository(ProofRegistrationRepository proofRegistrationRepository) {
        this.proofRegistrationRepository = proofRegistrationRepository;
    }

    @Autowired
    public void setRegistrationNumberService(RegistrationNumberService registrationNumberService) {
        this.registrationNumberService = registrationNumberService;
    }

    @Override
    public String getProofRegistrationNumber(boolean isTemporary) {
        return registrationNumberService.generateProofRegistrationNumber(isTemporary);
    }

    @Override
    public ProofRegistrationDto createProofRegistration(ProofRegistrationRequest proofRegistrationRequest) {
        setValidDateProofRegistration(proofRegistrationRequest.getProofRegistrationDto());
        return convertEntityToDto(proofRegistrationRepository.create(convertDtoToEntity(proofRegistrationRequest.getProofRegistrationDto())));
    }

    @Override
    public List<ProofRegistrationDto> getAllProofRegistrations() {
        return proofRegistrationRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ProofRegistrationDto searchByCardVehicleNumber(String cardVehicleNumber) {
        return convertEntityToDto(proofRegistrationRepository.findByNumberCardVehicle(cardVehicleNumber));
    }

    @Override
    public ProofRegistrationDto update(ProofRegistrationRequest proofRegistrationRequest) {
        ProofRegistrationDto proofRegistrationDto = new ProofRegistrationDto();
        proofRegistrationDto.setNumberCardVehicle(proofRegistrationRepository.update(convertDtoToEntity(proofRegistrationRequest.getProofRegistrationDto())).getNumberCardVehicle());
        return proofRegistrationDto;
    }

    @Override
    public ProofRegistrationDto finalizeRegistration(String vehicleCardNumber) {
        String newVehicleCardNumber = StringUtils.removeStart(vehicleCardNumber, "T");
        ProofRegistrationDto proofRegistrationDto = new ProofRegistrationDto();
        ProofRegistrationEntity entity = proofRegistrationRepository.findByNumberCardVehicle(vehicleCardNumber);
        entity.setNumberCardVehicle(newVehicleCardNumber);
        entity.setTemporaryProof(false);
        proofRegistrationRepository.update(entity);
        proofRegistrationDto.setNumberCardVehicle(newVehicleCardNumber);
        return proofRegistrationDto;
    }

    @Override
    public void removeProofRegistration(String numberCardVehicle) {
        ProofRegistrationEntity proofRegistrationEntity = proofRegistrationRepository.findByNumberCardVehicle(numberCardVehicle);
        proofRegistrationRepository.remove(proofRegistrationEntity);
    }

    @Override
    public Long getTemporaryRegistrationsCount() {
        return proofRegistrationRepository.getTemporaryRegistrationsCount();
    }

    @Override
    public Long getRegistrationsCount() {
        return proofRegistrationRepository.getRegistrationsCount();
    }

    private void setValidDateProofRegistration(ProofRegistrationDto proofRegistration){
        LocalDateTime validRegistrationDate;
        if(proofRegistration.isTemporaryProof()) {
            validRegistrationDate = LocalDateTime.ofInstant(proofRegistration.getRegistrationDate().toInstant(), ZoneId.systemDefault()).plusMonths(TEMP_VALID_TIME);
        }else{
            validRegistrationDate = LocalDateTime.ofInstant(proofRegistration.getRegistrationDate().toInstant(), ZoneId.systemDefault()).plusMonths(VALID_TIME);
        }
        proofRegistration.setRegistrationValidDate(Date.from(validRegistrationDate.atZone(ZoneId.systemDefault()).toInstant()));
    }

    private ProofRegistrationEntity convertDtoToEntity(ProofRegistrationDto proofRegistrationDto){
        ProofRegistrationEntity proofRegistrationEntity = new ProofRegistrationEntity();
        proofRegistrationEntity.setCar(carRepository.findByVin(proofRegistrationDto.getCarVin()));
        proofRegistrationEntity.setOtherOwner(proofRegistrationDto.getOtherOwnerPesels().
                stream().map(s -> personRepository.findByPesel(s)).collect(Collectors.toList()));
        proofRegistrationEntity.setFirstRegistrationDate(proofRegistrationDto.getFirstRegistrationDate());
        proofRegistrationEntity.setMainOwner(personRepository.findByPesel(proofRegistrationDto.getMainOwnerPesel()));
        proofRegistrationEntity.setNumberCardVehicle(proofRegistrationDto.getNumberCardVehicle());
        proofRegistrationEntity.setRegistrationDate(proofRegistrationDto.getRegistrationDate());
        proofRegistrationEntity.setRegistrationValidDate(proofRegistrationDto.getRegistrationValidDate());
        proofRegistrationEntity.setTemporaryProof(proofRegistrationDto.isTemporaryProof());
        proofRegistrationEntity.setPlate(plateRepository.findByPlateNumber(proofRegistrationDto.getPlateNumber()));
        return proofRegistrationEntity;
    }

    private ProofRegistrationDto convertEntityToDto(ProofRegistrationEntity proofRegistrationEntity){
        ProofRegistrationDto proofRegistrationDto = new ProofRegistrationDto();
        proofRegistrationDto.setRegistrationDate(proofRegistrationEntity.getRegistrationDate());
        proofRegistrationDto.setNumberCardVehicle(proofRegistrationEntity.getNumberCardVehicle());
        proofRegistrationDto.setTemporaryProof(proofRegistrationEntity.isTemporaryProof());
        proofRegistrationDto.setRegistrationValidDate(proofRegistrationEntity.getRegistrationValidDate());
        proofRegistrationDto.setCarVin(proofRegistrationEntity.getCar().getVin());
        proofRegistrationDto.setFirstRegistrationDate(proofRegistrationEntity.getFirstRegistrationDate());
        proofRegistrationDto.setMainOwnerPesel(proofRegistrationEntity.getMainOwner().getPesel());
        proofRegistrationDto.setOtherOwnerPesels(proofRegistrationEntity.getOtherOwner().stream()
                .map(PersonEntity::getPesel).collect(Collectors.toList()));
        proofRegistrationDto.setPlateNumber(proofRegistrationEntity.getPlate().getPlateNumber());
        return proofRegistrationDto;
    }

}
