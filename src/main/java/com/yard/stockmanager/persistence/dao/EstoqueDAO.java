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

    public static List<Estoque> getAll() {
        List list = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("select e.id, e.endereco, e.nome, e.descricao, e.telefone, e.ativo FROM Estoque e").list();
        s.getTransaction().commit();
        s.close();
        return list;
    }
}
