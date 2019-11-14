package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO extends Dao<Endereco> {
    public static List<Endereco> getAll() {
        List depList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        depList = s.createQuery("FROM Endereco").list();
        s.getTransaction().commit();
        s.close();
        return depList;
    }

    public static Endereco getById(int id) {

        Endereco e = new Endereco();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        e = (Endereco)s.createQuery("FROM Endereco WHERE id = '"+ id +"'").getSingleResult();
        s.getTransaction().commit();
        s.close();
        return e;

    }
}
