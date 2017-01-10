package com.app.registration.repository.CriteriaFilter;

import com.app.registration.model.AddressEntity;
import com.app.registration.model.CarEntity;
import com.app.registration.model.PersonEntity;

import java.util.Date;

/**
 * Created by konrad on 12.11.16.
 */
public class AgreementCriteriaFilter {

    private String city;
    private PersonEntity seller;
    private PersonEntity buyer;
    private CarEntity car;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PersonEntity getSeller() {
        return seller;
    }

    public void setSeller(PersonEntity seller) {
        this.seller = seller;
    }

    public PersonEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(PersonEntity buyer) {
        this.buyer = buyer;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
