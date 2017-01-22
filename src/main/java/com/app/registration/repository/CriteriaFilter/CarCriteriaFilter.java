package com.app.registration.repository.CriteriaFilter;

import com.app.registration.model.PersonEntity;
import com.app.registration.model.dto.CarDto;

import java.util.Date;

/**
 * Created by konrad on 22.01.17.
 */
public class CarCriteriaFilter {

    private String name;
    private String vin;
    private Date productionDate;
    private PersonEntity ownerPesel;

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

    public PersonEntity getOwnerPesel() {
        return ownerPesel;
    }

    public void setOwnerPesel(PersonEntity ownerPesel) {
        this.ownerPesel = ownerPesel;
    }

}
