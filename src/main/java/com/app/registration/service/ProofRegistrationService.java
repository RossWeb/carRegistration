package com.app.registration.service;

import com.app.registration.controller.request.ProofRegistrationRequest;
import com.app.registration.model.dto.ProofRegistrationDto;

import java.util.List;

/**
 * Created by konrad on 10.11.16.
 */
public interface ProofRegistrationService {

    String getProofRegistrationNumber(boolean isTemporary);
    ProofRegistrationDto createProofRegistration(ProofRegistrationRequest proofRegistrationRequest);
    List<ProofRegistrationDto> getAllProofRegistrations();
    ProofRegistrationDto searchByCardVehicleNumber(String cardVehicleNumber);
    ProofRegistrationDto update(ProofRegistrationRequest proofRegistrationRequest);
    void removeProofRegistration(String numberCardVehicle);
    Long getTemporaryRegistrationsCount();
    Long getRegistrationsCount();
}
