package com.app.registration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by konrad on 10.11.16.
 */
@Entity
@Table(name = "DOCUMENT")
@NamedQueries({
})
public class DocumentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private PersonEntity sender;
    private Date creationDate;
    private DocumentTypeEntity documentType;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "SENDER", length = 1000, nullable = false)
    public PersonEntity getSender() {
        return sender;
    }

    public void setSender(PersonEntity sender) {
        this.sender = sender;
    }

    @Column(name = "CREATION_DATE", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "DOCUMENT_TYPE_FK", length = 1000, nullable = false)
    public DocumentTypeEntity getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeEntity documentType) {
        this.documentType = documentType;
    }
}
