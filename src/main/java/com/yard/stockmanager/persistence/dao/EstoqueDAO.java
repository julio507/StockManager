package com.yard.stockmanager.persistence.dao;


import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO
        extends
            Dao<Estoque>
{

    public static List<Estoque> getAll(String busca) {
        List<Estoque> list;
        Endereco en = new Endereco();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("select new Estoque(e.id, e.endereco, e.nome, e.descricao, e.telefone, e.ativo) " +
                "FROM Estoque e, Endereco p " +
                "WHERE e.endereco = p.id and e.nome like '%" + busca + "%'").list();

        for (Estoque e : list){
            e.getId();
//            en.setId(list.get(0).getEndereco().getId());
//            en.setBairro(list.get(0).getEndereco().getBairro());
//            en.setCep(list.get(0).getEndereco().getCep());
//            en.setComplementos(list.get(0).getEndereco().getComplementos());
//            en.setNumero(list.get(0).getEndereco().getNumero());
//            en.setAtivo(list.get(0).getEndereco().getAtivo());
//            e.setEndereco(en);
            e.getEndereco().toString();
        }
        s.getTransaction().commit();
        s.close();
        return list;
    }

    public static Estoque getById(int id){
        Estoque e = new Estoque();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        e = (Estoque)s.createQuery("FROM Estoque WHERE id = '"+ id +"'").getSingleResult();
        s.getTransaction().commit();
        s.close();
        return e;
    }
}
