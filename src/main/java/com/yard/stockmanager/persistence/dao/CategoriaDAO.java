package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Categoria;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO
        extends
            Dao<Categoria> {

    public static List<Categoria> getAll() {
        List list = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("FROM Categoria").list();
        s.getTransaction().commit();
        s.close();
        return list;
    }
}
