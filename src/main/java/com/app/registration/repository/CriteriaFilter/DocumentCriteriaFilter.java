package com.app.registration.repository.CriteriaFilter;

import com.app.registration.model.CarEntity;
import com.app.registration.model.DocumentTypeEntity;
import com.app.registration.model.PersonEntity;

import java.util.Date;

/**
 * Created by konrad on 12.11.16.
 */
public class DocumentCriteriaFilter {

    private PersonEntity sender;
    private Date creationDate;
    private DocumentTypeEntity documentType;

    public PersonEntity getSender() {
        return sender;
    }

    public void setSender(PersonEntity sender) {
        this.sender = sender;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public DocumentTypeEntity getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeEntity documentType) {
        this.documentType = documentType;
    }
}
