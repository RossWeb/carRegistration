package com.app.registration.controller.request;

import com.app.registration.model.dto.ProofRegistrationDto;

/**
 * Created by konrad on 04.01.17.
 */
public class ProofRegistrationRequest extends AbstractRequest{

    private ProofRegistrationDto proofRegistrationDto;

    public ProofRegistrationDto getProofRegistrationDto() {
        return proofRegistrationDto;
    }

    public void setProofRegistrationDto(ProofRegistrationDto proofRegistrationDto) {
        this.proofRegistrationDto = proofRegistrationDto;
    }
}
