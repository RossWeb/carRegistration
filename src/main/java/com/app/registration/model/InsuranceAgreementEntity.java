package com.app.registration.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by konrad on 10.11.16.
 */
@Entity
@Table(name = "INSURANCE_AGREEMENTS")
@NamedQueries({
})
public class InsuranceAgreementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String insuranceNumber;
    private Date purchaseDate;
    private String insuranceCompanyName;
    private PersonEntity buyer;
    private List<PersonEntity> otherOwner;
    private CarEntity car;

    @Id
    @Column(name = "INSURANCE_NUMBER", nullable = false)
    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }


    @Column(name = "PURCHASE_DATE", nullable = false)
    @Type(type="date")
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Column(name = "INSURANCE_COMPANY", nullable = false)
    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    @OneToOne
    public PersonEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(PersonEntity buyer) {
        this.buyer = buyer;
    }

    @OneToOne
    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    @OneToMany
    public List<PersonEntity> getOtherOwner() {
        return otherOwner;
    }

    public void setOtherOwner(List<PersonEntity> otherOwner) {
        this.otherOwner = otherOwner;
    }
}
