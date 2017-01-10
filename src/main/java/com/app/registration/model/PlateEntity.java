package com.app.registration.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by konrad on 10.11.16.
 */

@Entity
@Table(name = "PLATES")
@NamedQueries({
})
public class PlateEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private String plateNumber;
    private String city;
    private boolean used;

    @Id
    @Column(name = "PLATE_NUMBER", nullable = false)
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Column(name = "CITY", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "USED", nullable = false)
    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
