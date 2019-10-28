package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Cidade;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CidadeDAO extends Dao<Cidade> {

    public static List<Cidade> getAll(String busca) {
        List cidList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        cidList = s.createQuery("FROM Cidade where nome like '%" + busca + "%'").list();
        s.getTransaction().commit();
        s.close();
        return cidList;
    }
}
