package com.app.registration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by konrad on 10.11.16.
 */
@Entity
@Table(name = "INVOICES")
@NamedQueries({
})
public class InvoiceEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date purchaseDate;
    private String shopName;
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

    @Column(name = "SHOP_NAME", nullable = false)
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public CarEntity getCar() {
        return car;
    }

    @Column(name = "CAR", length = 1000, nullable = false)
    public void setCar(CarEntity car) {
        this.car = car;
    }
}
