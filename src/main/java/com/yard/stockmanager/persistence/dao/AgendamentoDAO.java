package com.yard.stockmanager.persistence.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.yard.stockmanager.persistence.entity.Agendamento;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.Error;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class AgendamentoDAO extends Dao<Agendamento>
{
    public List<Agendamento> getForDate( LocalDate date )
    {
        List<Agendamento> list = new ArrayList<Agendamento>();

        try
        {
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
        }   

        catch( Exception e )
        {
            Error.exception(e);
        }

        finally
        {
            
        }

        return list;
    }
}