package com.mciefova.ataccamatask.connection.data.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class AbstractDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void create(Object data) {
        this.getSession().save(data);
    }

    public void saveOrUpdate(Object data) {
        this.getSession().saveOrUpdate(data);
    }

    public void update(Object data) {
        this.getSession().update(data);
    }

    public void delete(Object data) {
        this.getSession().delete(data);
    }
}
