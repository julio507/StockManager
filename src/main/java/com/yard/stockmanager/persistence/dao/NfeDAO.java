package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Marca;
import com.yard.stockmanager.persistence.entity.Nfe;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class NfeDAO extends Dao<Nfe> {

    public static List<Nfe> getAll() {
        List list = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("FROM Nfe").list();
        s.getTransaction().commit();
        s.close();
        return list;
    }
}

