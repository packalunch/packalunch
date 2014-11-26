package com.curry.fw.dao;

import com.curry.fw.model.AbstractEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/15/14.
 */
public class AbstractDao <T extends AbstractEntity> {

    private SessionFactory sessionFactory;

    @Autowired
    private void setSessionFactory(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    private final Class<T> type;

    public Class<T> getType() {
        return type;
    }

    public AbstractDao(Class<T> type) {
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public T findById(int id) {
        return (T) getSession().get(type, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getSession().createCriteria(type)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    public void delete(int id) {
        getSession().delete(findById(id));
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void save(T entity) {
        getSession().saveOrUpdate(entity);
    }

    public void merge(T entity) {
        getSession().merge(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

}
