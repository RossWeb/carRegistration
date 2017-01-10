package com.app.registration.repository;

import com.app.registration.model.DocumentEntity;
import com.app.registration.model.DocumentTypeEntity;
import com.app.registration.repository.CriteriaFilter.DocumentCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.DocumentTypeCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by konrad on 21.11.16.
 */
public class DocumentTypeRepositoryImpl extends AbstractGenericRepositoryWithSession<DocumentTypeEntity> implements DocumentTypeRepository {

    @Override
    public List<DocumentTypeEntity> find(DocumentTypeCriteriaFilter documentTypeCriteriaFilter) {
        Criteria documentTypeCriteria = getSession().createCriteria(DocumentTypeEntity.class);
        documentTypeCriteria.add(Restrictions.disjunction(
                Restrictions.eq("name",documentTypeCriteriaFilter.getName())));
        return documentTypeCriteria.list();
    }

    @Override
    public List<DocumentTypeEntity> findAll() {
        Criteria documentTypeCriteria = getSession().createCriteria(DocumentTypeEntity.class);
        return documentTypeCriteria.list();
    }
}
