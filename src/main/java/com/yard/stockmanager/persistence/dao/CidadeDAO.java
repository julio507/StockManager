package com.yard.stockmanager.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.yard.stockmanager.persistence.entity.Cidade;
import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;

import com.yard.stockmanager.useful.Error;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

    public List<Cidade> getPagination( String busca, int start, int end ) {

        List cidList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();

        Query q = s.createQuery("FROM Cidade where nome like '%" + busca + "%'");

        q.setFirstResult( start );
        q.setMaxResults( end );

        cidList = q.list();
        s.getTransaction().commit();
        s.close();
        return cidList;
    }

    public int addCidId (Cidade cid) {
        int result = 0;

        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.save(cid);
            result = (Integer) s.createQuery("SELECT max(id) FROM Cidade").uniqueResult();
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            Error.exception(e);
        }
        return result;
    }
    public static Cidade getById(int id) {

        Cidade c = new Cidade();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        c = (Cidade)s.createQuery("FROM Cidade WHERE id = '"+ id +"'").getSingleResult();
        s.getTransaction().commit();
        s.close();
        return c;

    }
}
