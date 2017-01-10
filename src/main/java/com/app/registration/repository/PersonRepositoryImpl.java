package com.app.registration.repository;

import com.app.registration.model.AddressEntity;
import com.app.registration.model.PersonEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import com.app.registration.repository.CriteriaFilter.PersonCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by konrad on 12.11.16.
 */
@Repository
public class PersonRepositoryImpl extends AbstractGenericRepositoryWithSession<PersonEntity> implements PersonRepository {

    @Override
    public List<PersonEntity> find(PersonCriteriaFilter personCriteriaFilter) {
        Criteria personCriteria = getSession().createCriteria(PersonEntity.class);
        personCriteria.add(Restrictions.disjunction(
                Restrictions.like("pesel",personCriteriaFilter.getPersonDto().getPesel(), MatchMode.ANYWHERE),
                Restrictions.like("name",personCriteriaFilter.getPersonDto().getName(), MatchMode.ANYWHERE),
                Restrictions.like("surname",personCriteriaFilter.getPersonDto().getSurname(), MatchMode.ANYWHERE),
                Restrictions.like("phoneNumber",personCriteriaFilter.getPersonDto().getPhoneNumber(), MatchMode.ANYWHERE)));
        return personCriteria.list();
    }

    @Override
    public List<PersonEntity> findAll() {
        Criteria personCriteria = getSession().createCriteria(PersonEntity.class);
        return personCriteria.list();
    }

    @Override
    public PersonEntity findByPesel(String pesel) {
        Criteria personCriteria =  getSession().createCriteria(PersonEntity.class);
        personCriteria.add(Restrictions.eq("pesel", pesel));
        return (PersonEntity) personCriteria.uniqueResult();
    }
}
