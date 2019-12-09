package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Marca;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MarcaDAO extends Dao<Marca> {

    public static List<Marca> getAll() {
        List list = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("FROM Marca").list();
        s.getTransaction().commit();
        s.close();
        return list;
    }

    public List<Marca> getPagination( String busca, int start, int end ) {

        List brandList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();

        Query q = s.createQuery("FROM Marca where nome like '%" + busca + "%'");

        q.setFirstResult( start );
        q.setMaxResults( end );

        brandList = q.list();
        s.getTransaction().commit();
        s.close();
        return brandList;
    }
}
