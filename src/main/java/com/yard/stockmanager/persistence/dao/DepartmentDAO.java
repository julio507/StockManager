package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Departamento;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import javafx.scene.control.TableView;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements Dao<Departamento> {

    @Override
    public void add(Departamento t) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(t);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public Departamento get(int id) {
        return null;
    }


    @Override
    public void delete(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Departamento dep = (Departamento) s.load(Departamento.class, id);
        s.delete(dep);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void update(Departamento departamento) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(departamento);
        s.getTransaction().commit();
        s.close();
    }

    public static List<Departamento> getAll() {
        List depList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        depList = s.createQuery("FROM Departamento").list();
        s.getTransaction().commit();
        s.close();
        return depList;
    }
}
