package com.app.registration.repository;

import com.app.registration.model.AddressEntity;
import com.app.registration.repository.CriteriaFilter.AddressCriteriaFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.expression.Expression;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by konrad on 12.11.16.
 */
@Repository
public class AddressRepositoryImpl extends AbstractGenericRepositoryWithSession<AddressEntity> implements AddressRepository {

    @Override
    public List<AddressEntity> find(AddressCriteriaFilter addressCriteriaFilter) {
        Criteria addressCriteria = getSession().createCriteria(AddressEntity.class);
        addressCriteria.add(Restrictions.disjunction(
                Restrictions.like("street",addressCriteriaFilter.getAddressDto().getStreet(), MatchMode.ANYWHERE),
                Restrictions.like("city",addressCriteriaFilter.getAddressDto().getCity(), MatchMode.ANYWHERE),
                Restrictions.like("postCode",addressCriteriaFilter.getAddressDto().getPostCode(), MatchMode.ANYWHERE)));
        return addressCriteria.list();
    }

    @Override
    public AddressEntity findById(Long id) {
        Criteria addressCriteria =  getSession().createCriteria(AddressEntity.class);
        addressCriteria.add(Restrictions.eq("id", id));
        return (AddressEntity) addressCriteria.uniqueResult();

    }

    @Override
    public List<AddressEntity> findAll() {
        Criteria addressCriteria =  getSession().createCriteria(AddressEntity.class);
        return addressCriteria.list();
    }
}
