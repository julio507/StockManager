package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Insercao;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class InsercaoDAO extends Dao<Insercao> {

    public static List<Insercao> getAll() {
        List insList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        insList = s.createQuery("FROM Insercao").list();
        s.getTransaction().commit();
        s.close();
        return insList;
    }

    public static List<Object[]> getinserts(String busca) {
        List<Object[]> insList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        insList = (List<Object[]>) s.createQuery("select distinct i.id, f.login, i.data, e.nome, (select sum(es2.quantidade) from EstoqueHasProduto es2 where es2.insercao = i.id) as produtos " +
                "from Funcionario f, EstoqueHasProduto es, Insercao i, Estoque e " +
                "where f.id = i.funcionario and es.insercao = i.id and e.id = es.estoque and e.nome like '%" + busca + "%'").list();
        s.getTransaction().commit();
        s.close();

        //debug
//        for(Object[] o: insList){
//            for(int i = 0; i < o.length; i++){
//                System.out.println(o[i]);
//                System.out.println(o[i].getClass());
//            }
//        }

        return insList;
    }

    public static List<Object[]> getinserts(int insercao) {
        List<Object[]> insList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        insList = (List<Object[]>) s.createQuery("select distinct i.id, f.login, i.data, e.nome, (select sum(es2.quantidade) from EstoqueHasProduto es2 where es2.insercao = i.id) as produtos " +
                "from Funcionario f, EstoqueHasProduto es, Insercao i, Estoque e " +
                "where f.id = i.funcionario and es.insercao = i.id and e.id = es.estoque and i.id = " + insercao ).list();
        s.getTransaction().commit();
        s.close();

        //debug
//        for(Object[] o: insList){
//            for(int i = 0; i < o.length; i++){
//                System.out.println(o[i]);
//                System.out.println(o[i].getClass());
//            }
//        }

        return insList;
    }

}
