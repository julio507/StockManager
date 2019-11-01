package com.yard.stockmanager.persistence.dao;
import com.yard.stockmanager.persistence.entity.Pessoa;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PeopleRegisterDAO
    extends
    Dao<Pessoa> {
    public static List<Pessoa> getAll() {
        List list = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("select d.id, d.endereco, d.denominacaosocial, d.nome, d.email, d.cnpj, d.cpf, d.observacoes, d.ativo FROM Pessoa d").list();
        s.getTransaction().commit();
        s.close();
        return list;
    }
}





