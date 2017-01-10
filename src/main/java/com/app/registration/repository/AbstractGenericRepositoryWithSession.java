package com.app.registration.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 2016-11-05.
 */

public abstract class AbstractGenericRepositoryWithSession<T extends Object>  {

    private static final Logger LOG = LoggerFactory.logger(AbstractGenericRepositoryWithSession.class);

    @Autowired
    private SessionFactory sessionFactory;


    public T create(T entity) {
        getSession().save(entity);
        getSession().flush();
        return entity;
    }

    public T update(T entity) {
        getSession().saveOrUpdate(entity);
        getSession().flush();
        return entity;
    }

    public void remove(T entity) {
        getSession().delete(entity);
        getSession().flush();
    }

    protected Session getSession() {
        Session currentSession = sessionFactory.getCurrentSession();
        if (currentSession == null) {
            LOG.info("Session is null!");
        }
        return currentSession;
    }
}
