/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Funcionario;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julio
 */

//link do video com o codigo fonte utilização: https://youtu.be/ZOIjNF4Yr20?list=PLR7lDCnFejO_vlNeE6Ghl6gps38MvJiTm
public class FuncionarioDAO
        implements
            Dao<Funcionario>
{
    @Override
    public void add(Funcionario t)
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(t);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public Funcionario get(int id) {
        return null;
    }


    @Override
    public void delete(int id)
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Funcionario func = (Funcionario) s.load(Funcionario.class, id);
        s.delete(func);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void update(Funcionario funcionario) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(funcionario);
        s.getTransaction().commit();
        s.close();
    }

    public boolean doLogin( String login, String password )
    {
        try 
        {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<Funcionario> func = new ArrayList<>();
            String hql = "FROM Funcionario f WHERE f.login = :login and f.senha = :senha";
            Query query = s.createQuery(hql);
            query.setParameter("login", login);
            query.setParameter("senha", password);
            s.beginTransaction();
            func = query.list();
            s.close();

            if (func.get(0).getLogin().equals(login) && func.get(0).getSenha().equals(password))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            
            return false;
        }
    }
}
