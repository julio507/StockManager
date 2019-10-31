package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Unidade;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UnidadeDAO extends Dao<Unidade> {

    public static List<Unidade> getAll() {
        List list = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("FROM Unidade").list();
        s.getTransaction().commit();
        s.close();
        return list;
    }
}
