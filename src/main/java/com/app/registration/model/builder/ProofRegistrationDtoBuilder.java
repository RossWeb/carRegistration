package com.app.registration.model.builder;

import com.app.registration.controller.request.ProofRegistrationRequest;
import com.app.registration.model.dto.ProofRegistrationDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by konrad on 14.01.17.
 */
public class ProofRegistrationDtoBuilder {

    private String numberCardVehicle;
    private String plateNumber;
    private Date firstRegistrationDate;
    private String mainOwnerPesel;
    private List<String> otherOwnerPesels = new ArrayList<>();
    private Date registrationDate;
    private Date registrationValidDate;
    private String carVin;
    private boolean temporaryProof;


    public ProofRegistrationDtoBuilder setRegistrationDateCredentials(Date firstDateRegistration, Date registrationDate){
        this.firstRegistrationDate = firstDateRegistration;
        this.registrationDate = registrationDate;
        return this;
    }

    public ProofRegistrationDtoBuilder setOwnerAndCar(String ownerPesel, String carVin){
        this.mainOwnerPesel = ownerPesel;
        this.carVin = carVin;
        return this;
    }

    public ProofRegistrationDtoBuilder addOtherOwner(String otherOwnerPesel){
        this.otherOwnerPesels.add(otherOwnerPesel);
        return this;
    }

    public ProofRegistrationDtoBuilder setRegistrationMainCredentials(String numberVehicleCard, String plateNumber, boolean isTemporary){
        this.numberCardVehicle = numberVehicleCard;
        this.temporaryProof = isTemporary;
        this.plateNumber = plateNumber;
        return this;
    }

    public ProofRegistrationDto build(){
        ProofRegistrationDto proofRegistrationDto = new ProofRegistrationDto();
        proofRegistrationDto.setMainOwnerPesel(mainOwnerPesel);
        proofRegistrationDto.setOtherOwnerPesels(otherOwnerPesels);
        proofRegistrationDto.setPlateNumber(plateNumber);
        proofRegistrationDto.setTemporaryProof(temporaryProof);
        proofRegistrationDto.setCarVin(carVin);
        proofRegistrationDto.setFirstRegistrationDate(firstRegistrationDate);
        proofRegistrationDto.setNumberCardVehicle(numberCardVehicle);
        proofRegistrationDto.setRegistrationDate(registrationDate);
        proofRegistrationDto.setRegistrationValidDate(registrationValidDate);
        return proofRegistrationDto;
    }

    public ProofRegistrationRequest decorateRequest(){
        ProofRegistrationRequest proofRegistrationRequest = new ProofRegistrationRequest();
        proofRegistrationRequest.setProofRegistrationDto(build());
        return proofRegistrationRequest;
    }
}
