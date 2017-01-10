package com.app.registration.model.dto;

import com.app.registration.model.PersonEntity;

import java.util.Date;

/**
 * Created by konrad on 27.11.16.
 */
public class CarDto {

    private String name;
    private String vin;
    private Date productionDate;
    private String ownerPesel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getOwnerPesel() {
        return ownerPesel;
    }

    public void setOwnerPesel(String ownerPesel) {
        this.ownerPesel = ownerPesel;
    }
}
