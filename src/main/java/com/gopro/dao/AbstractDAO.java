package com.gopro.dao;
 
import java.io.*;
import java.util.*;
import org.hibernate.*;
 
@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T> {
 
    private Class<T> entityClass;
    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
 
    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
 
    public AbstractDAO() {
    }
 
    public List<T> findAll() {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()){
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getName()).list();
        } catch (RuntimeException re) {
            return null;
        }
    }
 
    public Boolean update(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            sessionFactory.getCurrentSession().merge(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return true;
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return false;
            //throw re;
        }
    }
 
    public Boolean delete(T instance) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            sessionFactory.getCurrentSession().delete(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return true;
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return false;
        }
    }
    
    public T create(T instance) {

        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            sessionFactory.getCurrentSession().persist(instance);
            sessionFactory.getCurrentSession().getTransaction().commit();
            return instance;
            
        } catch (RuntimeException re) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            return null;
            //throw re;
        }
        
    }
 
    public T find(Object primarykey) {
        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive())
                sessionFactory.getCurrentSession().getTransaction().begin();            
            return (T) sessionFactory.getCurrentSession().get(entityClass,
                    (Serializable) primarykey);
        } catch (RuntimeException re) {
            return null;
        }
    }
}