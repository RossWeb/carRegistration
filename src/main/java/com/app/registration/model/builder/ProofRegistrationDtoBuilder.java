package com.app.registration.model.builder;

import com.app.registration.controller.request.ProofRegistrationRequest;
import com.app.registration.model.dto.ProofRegistrationDto;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by konrad on 14.01.17.
 */
public class ProofRegistrationDtoBuilder {

    private ProofRegistrationDto proofRegistrationDto = new ProofRegistrationDto();

    public ProofRegistrationDtoBuilder(){
        proofRegistrationDto.setOtherOwnerPesels(new ArrayList<>());
    }

    public ProofRegistrationDtoBuilder setRegistrationDateCredentials(Date firstDateRegistration, Date registrationDate){
        proofRegistrationDto.setFirstRegistrationDate(firstDateRegistration);
        proofRegistrationDto.setRegistrationDate(registrationDate);
        return this;
    }

    public ProofRegistrationDtoBuilder setOwnerAndCar(String ownerPesel, String carVin){
        proofRegistrationDto.setMainOwnerPesel(ownerPesel);
        proofRegistrationDto.setCarVin(carVin);
        return this;
    }

    public ProofRegistrationDtoBuilder addOtherOwner(String otherOwnerPesel){
        proofRegistrationDto.getOtherOwnerPesels().add(otherOwnerPesel);
        return this;
    }

    public ProofRegistrationDtoBuilder setRegistrationMainCredentials(String numberVehicleCard, String plateNumber, boolean isTemporary){
        proofRegistrationDto.setNumberCardVehicle(numberVehicleCard);
        proofRegistrationDto.setTemporaryProof(isTemporary);
        proofRegistrationDto.setPlateNumber(plateNumber);
        return this;
    }

    public ProofRegistrationDto build(){
        return proofRegistrationDto;
    }

    public ProofRegistrationRequest decorateRequest(){
        ProofRegistrationRequest proofRegistrationRequest = new ProofRegistrationRequest();
        proofRegistrationRequest.setProofRegistrationDto(proofRegistrationDto);
        return proofRegistrationRequest;
    }
}
