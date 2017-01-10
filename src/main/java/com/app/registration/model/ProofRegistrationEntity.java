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
@Table(name = "PROOF_REGISTRATION")
@NamedQueries({
})
public class ProofRegistrationEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String numberCardVehicle;
    private PlateEntity plate;
    private Date firstRegistrationDate;
    private PersonEntity mainOwner;
    private List<PersonEntity> otherOwner;
    private Date registrationDate;
    private Date registrationValidDate;
    private CarEntity car;
    private boolean isTemporaryProof;

    @Id
    @Column(name = "NUMBER_CARD_VEHICLE", nullable = false)
    public String getNumberCardVehicle() {
        return numberCardVehicle;
    }

    public void setNumberCardVehicle(String numberCardVehicle) {
        this.numberCardVehicle = numberCardVehicle;
    }

    @OneToOne
    public PlateEntity getPlate() {
        return plate;
    }

    public void setPlate(PlateEntity plate) {
        this.plate = plate;
    }

    @Column(name = "FIRST_REGISTRATION_DATE", nullable = false)
    @Type(type="date")
    public Date getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public void setFirstRegistrationDate(Date firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }

    @Column(name = "REGISTRATION_VALID_DATE", nullable = false)
    @Type(type="date")
    public Date getRegistrationValidDate() {
        return registrationValidDate;
    }

    public void setRegistrationValidDate(Date registrationValidDate) {
        this.registrationValidDate = registrationValidDate;
    }

    @OneToOne
    public PersonEntity getMainOwner() {
        return mainOwner;
    }

    public void setMainOwner(PersonEntity mainOwner) {
        this.mainOwner = mainOwner;
    }

    @OneToMany
    public List<PersonEntity> getOtherOwner() {
        return otherOwner;
    }

    public void setOtherOwner(List<PersonEntity> otherOwner) {
        this.otherOwner = otherOwner;
    }

    @Column(name = "REGISTRATION_DATE", nullable = false)
    @Type(type="date")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @OneToOne
    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    @Column(name = "TEMPORARY_PROOF", nullable = false)
    public boolean isTemporaryProof() {
        return isTemporaryProof;
    }

    public void setTemporaryProof(boolean isTemporaryProof) {
        this.isTemporaryProof = isTemporaryProof;
    }
}
