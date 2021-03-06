package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Unidade;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

    public List<Unidade> getPagination( String busca, int start, int end ) {

        List unityList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();

        Query q = s.createQuery("FROM Unidade where nome like '%" + busca + "%'");

        q.setFirstResult( start );
        q.setMaxResults( end );

        unityList = q.list();
        s.getTransaction().commit();
        s.close();
        return unityList;
    }
}
