package com.app.registration.controller.request;

/**
 * Created by konrad on 07.01.17.
 */
public class ProofRegistrationTemplateDataRequest extends AbstractRequest {

    private String pesel;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
