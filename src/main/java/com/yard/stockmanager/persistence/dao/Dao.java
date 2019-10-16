package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.hibernate.HibernateUtil;

import org.hibernate.Session;

public abstract class Dao<T>
{
    public void add(T t)
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(t);
        s.getTransaction().commit();
        s.close();
    };

    public T get(int id )
    {
        return null;
    };

    public void delete( T t )
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete( t );
        s.getTransaction().commit();
        s.close();
    };

    public void update( T t )
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(t);
        s.getTransaction().commit();
        s.close();
    };
}
