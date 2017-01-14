package com.app.registration.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by konrad on 10.11.16.
 */

@Entity
@Table(name = "PLATES")
@NamedQueries({
        @NamedQuery(name = PlateEntity.GET_UNUSED_LESS_BY_COUNT, query = "select count(*), p.signs from PlateEntity p where p.used = false group by p.signs having count(*) < :minCountValue")
})
public class PlateEntity implements Serializable{

    public static final String GET_UNUSED_LESS_BY_COUNT = "PlateEntity.findByCountGroupBy";
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

    @Column(name = "SIGNS", nullable = false, length = 2)
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
