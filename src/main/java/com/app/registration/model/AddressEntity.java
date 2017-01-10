package com.app.registration.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by konrad on 12.11.16.
 */
@Entity
@Table(name = "ADDRESS")
@NamedQueries({
})
public class AddressEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String street;
    private String postCode;
    private String city;
    private String signs;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "STREET", nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "POST_CODE", nullable = false)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(name = "CITY", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "SIGNS", nullable = false)
    public String getSigns() {
        return signs;
    }

    public void setSigns(String signs) {
        this.signs = signs;
    }
}
