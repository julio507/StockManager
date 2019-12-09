package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Marca;
import com.yard.stockmanager.persistence.entity.Nfe;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.Error;
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

    public int addReturnid(Nfe nfe) {

        int result = 0;

        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.save(nfe);
            result = (Integer) s.createQuery("SELECT max(id) FROM Nfe").uniqueResult();
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            Error.exception(e);
        }

        return result;
    }

}

