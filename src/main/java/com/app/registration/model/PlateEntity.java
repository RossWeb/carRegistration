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
    private String signs;
    private boolean used;

    @Id
    @Column(name = "PLATE_NUMBER", nullable = false)
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Column(name = "SIGNS", nullable = false)
    public String getSigns() {
        return signs;
    }

    public void setSigns(String signs) {
        this.signs = signs;
    }

    @Column(name = "USED", nullable = false)
    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
