package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.EstoqueHasProduto;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class EstoqueHasProdutoDAO extends Dao<EstoqueHasProduto> {

    public static List<EstoqueHasProduto> getAll() {
        List insList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        insList = s.createQuery("FROM EstoqueHasProduto").list();
        s.getTransaction().commit();
        s.close();
        return insList;
    }

    public static List<Object[]> getinserts(String busca) {
        List<Object[]> insList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        insList = (List<Object[]>) s.createQuery("select distinct p.id, p.nome, m.nome, u.sigla, es.quantidade, es.valorunitario " +
                "from Produto p, EstoqueHasProduto es, Insercao i, Marca m, Unidade u " +
                "where p.id = es.produto and es.insercao = 1 and p.marca = m.id and u.id = p.unidade and p.nome like '%" + busca + "%'").list();
        s.getTransaction().commit();
        s.close();

        //debug
        for(Object[] o: insList){
            for(int i = 0; i < o.length; i++){
                System.out.println(o[i]);
                System.out.println(o[i].getClass());
            }
        }

        return insList;
    }
}
