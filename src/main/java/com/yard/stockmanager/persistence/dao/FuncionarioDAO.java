/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.yard.stockmanager.persistence.entity.Funcionario;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.Current;
import com.yard.stockmanager.useful.Encoding;
import com.yard.stockmanager.useful.Error;

import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Julio
 */

//link do video com o codigo fonte utilização: https://youtu.be/ZOIjNF4Yr20?list=PLR7lDCnFejO_vlNeE6Ghl6gps38MvJiTm
public class FuncionarioDAO
        extends
            Dao<Funcionario>
{
    public boolean doLogin( String login, String password )
    {
        try 
        {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<Funcionario> func = new ArrayList<>();
            String hql = "FROM Funcionario f WHERE f.login = :login and f.senha = :senha";
            Query query = s.createQuery(hql);
            query.setParameter("login", login);
            query.setParameter("senha", Encoding.encodeToMD5( password ));
            s.beginTransaction();
            func = query.list();
            s.close();

            if (func.get(0).getLogin().equals(login) && func.get(0).getSenha().equals( Encoding.encodeToMD5(password)))
            {
                Current.setUser(func.get(0).getId());
                return true;
            }
            else
            {
                return false;
            }
        }
        
        catch (Exception e)
        {
            Error.exception(e);

            return false;
        }
    }

    public static Funcionario getById(int id) {

        Funcionario f = new Funcionario();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        f = (Funcionario)s.createQuery("FROM Funcionario WHERE id = '"+ id +"'").getSingleResult();
        s.getTransaction().commit();
        s.close();
        return f;
    }

    public static Funcionario getByLogin(String login) {

        Funcionario f = new Funcionario();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        f = (Funcionario)s.createQuery("FROM Funcionario WHERE login = '"+ login +"'").getSingleResult();
        s.getTransaction().commit();
        s.close();
        return f;
    }
}
