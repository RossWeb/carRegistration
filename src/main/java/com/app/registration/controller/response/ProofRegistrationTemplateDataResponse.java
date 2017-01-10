package com.app.registration.controller.response;

import java.util.List;

/**
 * Created by konrad on 07.01.17.
 */
public class ProofRegistrationTemplateDataResponse extends AbstractResponse {

    private String cardVehicleNumber;
    private List<String> plateNumbers;
    private boolean isTemporary = true;

    public boolean isTemporary() {
        return isTemporary;
    }

    public void setTemporary(boolean temporary) {
        isTemporary = temporary;
    }

    public String getCardVehicleNumber() {
        return cardVehicleNumber;
    }

    public void setCardVehicleNumber(String cardVehicleNumber) {
        this.cardVehicleNumber = cardVehicleNumber;
    }

    public List<String> getPlateNumbers() {
        return plateNumbers;
    }

    public void setPlateNumbers(List<String> plateNumbers) {
        this.plateNumbers = plateNumbers;
    }
}
