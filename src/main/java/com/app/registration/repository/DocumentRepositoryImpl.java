package com.app.registration.repository;

import com.app.registration.model.DocumentEntity;
import com.app.registration.repository.CriteriaFilter.DocumentCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by konrad on 21.11.16.
 */
public class DocumentRepositoryImpl extends AbstractGenericRepositoryWithSession<DocumentEntity> implements DocumentRepository {

    @Override
    public List<DocumentEntity> find(DocumentCriteriaFilter documentCriteriaFilter) {
        Criteria documentCriteria = getSession().createCriteria(DocumentEntity.class);
        documentCriteria.add(Restrictions.disjunction(
                Restrictions.eq("sender",documentCriteriaFilter.getSender()),
                Restrictions.eq("creationDate",documentCriteriaFilter.getCreationDate()),
                Restrictions.like("documentType",documentCriteriaFilter.getDocumentType())));
        return documentCriteria.list();
    }

    @Override
    public List<DocumentEntity> findAll() {
        Criteria documentCriteria = getSession().createCriteria(DocumentEntity.class);
        return documentCriteria.list();
    }
}
