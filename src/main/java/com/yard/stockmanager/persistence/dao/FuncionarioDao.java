/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Funcionario;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Julio
 */
public class FuncionarioDao
        implements
            Dao<Funcionario>
{
    @Override
    public void add(Funcionario t)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Funcionario> get(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

            String sql = "select * from user "
                    + " where login = '" + login + "' "
                    + " and password = md5('" + password + "');";

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
