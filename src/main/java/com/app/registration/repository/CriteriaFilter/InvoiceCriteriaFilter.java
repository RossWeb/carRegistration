package com.app.registration.repository.CriteriaFilter;

import com.app.registration.model.AddressEntity;
import com.app.registration.model.CarEntity;
import com.app.registration.model.PersonEntity;

/**
 * Created by konrad on 12.11.16.
 */
public class InvoiceCriteriaFilter {

    private String shopName;
    private PersonEntity buyer;
    private CarEntity car;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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
