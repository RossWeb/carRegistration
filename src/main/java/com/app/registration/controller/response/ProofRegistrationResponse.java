package com.app.registration.controller.response;

import com.app.registration.model.dto.ProofRegistrationDto;

/**
 * Created by konrad on 07.01.17.
 */
public class ProofRegistrationResponse extends AbstractResponse {

    private ProofRegistrationDto proofRegistrationDto;

    public ProofRegistrationDto getProofRegistrationDto() {
        return proofRegistrationDto;
    }

    public void setProofRegistrationDto(ProofRegistrationDto proofRegistrationDto) {
        this.proofRegistrationDto = proofRegistrationDto;
    }
}
