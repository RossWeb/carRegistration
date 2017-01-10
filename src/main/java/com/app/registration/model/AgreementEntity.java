package com.app.registration.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by konrad on 10.11.16.
 */
@Entity
@Table(name = "AGREEMENT")
@NamedQueries({
})
public class AgreementEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date purchaseDate;
    private String city;
    private PersonEntity seller;
    private PersonEntity buyer;
    private Double carPrice;
    private CarEntity car;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "PURCHASE_DATE", nullable = false)
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Column(name = "CITY", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "SELLER", length = 1000, nullable = false)
    public PersonEntity getSeller() {
        return seller;
    }

    public void setSeller(PersonEntity seller) {
        this.seller = seller;
    }

    @Column(name = "BUYER", length = 1000, nullable = false)
    public PersonEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(PersonEntity buyer) {
        this.buyer = buyer;
    }

    @Column(name = "CAR_PRICE", nullable = false)
    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    @Column(name = "CAR", length = 1000, nullable = false)
    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
