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

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julio
 */

//link do video com o codigo fonte utilização: https://youtu.be/ZOIjNF4Yr20?list=PLR7lDCnFejO_vlNeE6Ghl6gps38MvJiTm
public class FuncionarioDao
        implements
            Dao<Funcionario>
{
    @Override
    public void add(Funcionario t)
    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(t);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public List<Funcionario> get(int id)
    {
        List<Funcionario> list = new ArrayList<Funcionario>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("From funcionario where id = "+ id).list();
        s.getTransaction().commit();
        s.close();
        return  list;
    }


    public List<Funcionario> getAll(int id)
    {
        List<Funcionario> list = new ArrayList<Funcionario>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("From funcionario").list();
        s.getTransaction().commit();
        s.close();
        return  list;
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
/*            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes(), 0, password.length());

            EntityManager manager = Factory.getManager();

            manager.getTransaction().begin();
            
            Query query = manager.createQuery
            (
                "select id " +
                "from Funcionario " +
                "where " +
                "Funcionario.Login = " + login +
                " and " +
                "Funcionario.Senha = md5( " + password + " )"
            );

            manager.getTransaction().commit();
            
            return query.getResultList().size() == 1;*/
            
            int i = 0;

            Statement st = DBConnection.getInstance().getConnection().createStatement();

            String sql = "select id from funcionario "
                    + " where login = '" + login + "' "
                    + " and senha = md5('" + password + "');";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next())
            {

                i++;
            }

            return i == 1;
            
        }

        catch (Exception e)
        {
            System.out.println(e);
            
            return false;
        }
    }
}
