package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.Error;

import org.hibernate.Session;

public abstract class Dao<T> {
    public void add(T t) {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.save(t);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            Error.exception(e);
        }

    };

    public void delete(T t) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(t);
        s.getTransaction().commit();
        s.close();
    };

    public T get( Class<T> c, int id )
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        T o = s.get( c, id);
        s.close();

        return o;
    }

    public void update(T t) {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            System.out.println(t);
            s.update(t);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            Error.exception(e);
        }
    };
}
