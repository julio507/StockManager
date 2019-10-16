package com.yard.stockmanager.persistence.dao;


import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO
        extends
            Dao<Estoque>
{

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
