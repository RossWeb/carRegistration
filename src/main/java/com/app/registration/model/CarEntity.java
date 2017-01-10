package com.app.registration.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 2016-11-05.
 */
@Entity
@Table(name = "Car")
@NamedQueries({
        @NamedQuery(name = CarEntity.FIND_BY_VIN, query = "SELECT gr FROM CarEntity gr WHERE gr.vin = :vin"),
        @NamedQuery(name = CarEntity.FIND_ALL, query = "SELECT gr FROM CarEntity gr"),
})
public class CarEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_VIN = "CarEntity.findByVin";
    public static final String FIND_ALL = "CarEntity.findAll";

    private Long id;
    private String name;
    private String vin;
    private Date productionDate;
    private PersonEntity owner;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Column
    @Type(type="date")
    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    @ManyToOne
    @JoinColumn(name="owner_id")
    public PersonEntity getOwner() {
        return owner;
    }

    public void setOwner(PersonEntity owner) {
        this.owner = owner;
    }
}
