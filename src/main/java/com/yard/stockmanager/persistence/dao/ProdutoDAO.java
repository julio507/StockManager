package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Produto;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProdutoDAO extends
        Dao<Produto> {
    public static List<Produto> getAll(String busca) {

        List<Produto> list;
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("select new Produto (p.id, p.categoria, p.departamento, p.marca, p.unidade, p.nome, p.descricao, p.quantidade, p.custounitario, p.ativo) " +
                "FROM Produto p, Categoria c, Departamento d, Marca m, Unidade u WHERE p.categoria = c.id and p.departamento = d.id " +
                "and p.marca = m.id and p.unidade = u.id and p.nome like '%" + busca + "%'").list();

        //inicialização das classes auxiliares
        for (Produto p : list) {
            p.getId();
            p.getCategoria().toString();
            p.getDepartamento().toString();
            p.getMarca().toString();
            p.getUnidade().toString();
        }
        s.getTransaction().commit();
        s.close();

        return list;
    }

    public static Produto getById(int id) {

        Produto p = new Produto();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        p = (Produto) s.createQuery("select new Produto (p.id, p.categoria, p.departamento, p.marca, p.unidade, p.nome, p.descricao, p.quantidade, p.custounitario, p.ativo) " +
                "FROM Produto p, Categoria c, Departamento d, Marca m, Unidade u WHERE p.categoria = c.id and p.departamento = d.id " +
                "and p.marca = m.id and p.unidade = u.id and p.id = " + id).getSingleResult();

        //inicialização das classes auxiliares
        p.getId();
        p.getCategoria().toString();
        p.getDepartamento().toString();
        p.getMarca().toString();
        p.getUnidade().toString();

        s.getTransaction().commit();
        s.close();
        return p;

    }
}
