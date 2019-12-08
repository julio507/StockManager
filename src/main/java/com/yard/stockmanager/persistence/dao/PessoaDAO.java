package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.entity.Pessoa;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.Error;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO extends Dao<Pessoa> {
    Pessoa pes = new Pessoa();
    public static List<Endereco> getAll() {
        List pesList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        pesList = s.createQuery("FROM Pessoa").list();
        s.getTransaction().commit();
        s.close();
        return pesList;
    }
    public static List<Pessoa> getOnllyAddres (){
        List pesList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        pesList = s.createQuery("SELECT p.nome,p.descricao FROM Pessoa p").list();
        s.getTransaction().commit();
        s.close();
        return pesList;
    }









}
