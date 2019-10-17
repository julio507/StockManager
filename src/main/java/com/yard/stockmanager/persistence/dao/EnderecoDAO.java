package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Departamento;
import com.yard.stockmanager.persistence.entity.Endereco;
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
}
