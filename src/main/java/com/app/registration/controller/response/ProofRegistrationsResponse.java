package com.app.registration.controller.response;

import com.app.registration.model.dto.ProofRegistrationDto;

import java.util.List;

/**
 * Created by konrad on 07.01.17.
 */
public class ProofRegistrationsResponse extends AbstractResponse {

    private Long temporaryProofRegistrationsCount;
    private Long proofRegistrationsCount;
    private List<ProofRegistrationDto> proofRegistrationList;

    public Long getTemporaryProofRegistrationsCount() {
        return temporaryProofRegistrationsCount;
    }

    public void setTemporaryProofRegistrationsCount(Long temporaryProofRegistrationsCount) {
        this.temporaryProofRegistrationsCount = temporaryProofRegistrationsCount;
    }

    public Long getProofRegistrationsCount() {
        return proofRegistrationsCount;
    }

    public void setProofRegistrationsCount(Long proofRegistrationsCount) {
        this.proofRegistrationsCount = proofRegistrationsCount;
    }

    public List<ProofRegistrationDto> getProofRegistrationList() {
        return proofRegistrationList;
    }

    public void setProofRegistrationList(List<ProofRegistrationDto> proofRegistrationList) {
        this.proofRegistrationList = proofRegistrationList;
    }
}
