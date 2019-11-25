package com.yard.stockmanager.persistence.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.yard.stockmanager.persistence.entity.Agendamento;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.DateFormat;
import com.yard.stockmanager.useful.Error;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class AgendamentoDAO extends Dao<Agendamento> {
    public List<Agendamento> getInMonth(LocalDate date) {
        List<Agendamento> list = new ArrayList<Agendamento>();

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();

            Query<Agendamento> q = s.createQuery("FROM Agendamento WHERE data >= :start AND data <= :end");

            s.setProperty("start", DateFormat.toDate(date.withDayOfMonth(1)));
            s.setProperty("end", date.withDayOfMonth(date.lengthOfMonth()));

            list = q.list();
        }

        catch (Exception e) {
            Error.exception(e);
        }

        finally {
            s.close();
        }

        return list;
    }

    public List<Agendamento> getForDate(LocalDate date) {
        List<Agendamento> list = new ArrayList<Agendamento>();

        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            s.beginTransaction();

            Query<Agendamento> q = s.createQuery("FROM Agendamento WHERE data = :date");

            q.setParameter("date", DateFormat.toDate(date));

            list = q.list();

            s.getTransaction().commit();
        }

        catch (Exception e) {
            Error.exception(e);
        }

        finally {
            s.close();
        }

        return list;
    }
}