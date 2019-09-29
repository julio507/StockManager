package com.yard.stockmanager.persistence.dao;


import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO
        implements
            Dao<Estoque>
{
    @Override
    public void add(Estoque estoque)
    {

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(estoque);
        s.getTransaction().commit();
        s.close();
        /*try
        {
            String sql = "insert into estoque value ( default, 1, '" + estoque.getNome() + "', '" + estoque.getDescricao() + "', '" + estoque.getTelefone() + "')";
            System.out.println(sql);
        }

        catch (Exception e)
        {
            System.out.println(e);
        }*/
    }

    @Override
    public Estoque get(int id) {
        return null;
    }


    @Override
    public void delete(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Estoque est = (Estoque) s.load(Estoque.class, id);
        s.delete(est);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void update(Estoque estoque) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(estoque);
        s.getTransaction().commit();
        s.close();
    }

    public List<Estoque> getAll()
    {

        List depList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        depList = s.createQuery("FROM Estoque").list();
        s.getTransaction().commit();
        s.close();
        return depList;

        /*List<Estoque> list = new ArrayList<>();

        try
        {
            String sql = "select * from estoque";


        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return list;*/
    }
}
