package com.app.registration.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by konrad on 10.11.16.
 */
@Entity
@Table(name = "DOCUMENTS_TYPES")
@NamedQueries({
})
public class DocumentTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
