package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.Error;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO extends Dao<Endereco> {
    Endereco end = new Endereco();
    public static List<Endereco> getAll() {
        List depList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        depList = s.createQuery("FROM Endereco").list();
        s.getTransaction().commit();
        s.close();
        return depList;
    }

    public static Endereco getById(int id) {

        Endereco e = new Endereco();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        e = (Endereco) s.createQuery("FROM Endereco WHERE id = '" + id + "'").getSingleResult();

        e.setCidade(CidadeDAO.getById(e.getCidade().getId()));

        s.getTransaction().commit();
        s.close();
        return e;

    }
    public int addEndId (Endereco end) {
        int result = 0;

        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.save(end);
            result = (Integer) s.createQuery("SELECT max(id) FROM Endereco").uniqueResult();
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            Error.exception(e);
        }
        return result;
    }

    public static List<Endereco> getOnllyAddres (){
        List addrList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        addrList = s.createQuery("SELECT e.id, e.rua, e.numero,e.cep, e.complementos, c.nome FROM Endereco e, Cidade c WHERE e.cidade.id = c.id").list();
        s.getTransaction().commit();
        s.close();
        return addrList;
    }

    public String endereco() {
        return end.getRua() + " - ," + end.getBairro() + " - ," + end.getCidade();
    }
}
