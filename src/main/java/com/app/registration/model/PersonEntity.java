package com.app.registration.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by konrad on 10.11.16.
 */
@Entity
@Table(name = "PERSON")
@NamedQueries({
})
public class PersonEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String pesel;
    private String name;
    private String surname;
    private String phoneNumber;
    private AddressEntity address;

    @Id
    @Column(name = "PESEL", length = 11, nullable = false)
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SURNAME", nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "PHONE", length = 9)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
