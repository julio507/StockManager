package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.EstoqueHasProduto;
import com.yard.stockmanager.persistence.entity.EstoqueHasProdutoId;
import com.yard.stockmanager.persistence.entity.Insercao;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.Error;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class EstoqueHasProdutoDAO extends Dao<EstoqueHasProduto> {

    //traz todos os registros da entidade
    public static List<EstoqueHasProduto> getAll() {
        List insList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        insList = s.createQuery("FROM EstoqueHasProduto").list();
        s.getTransaction().commit();
        s.close();
        return insList;
    }

    //traz todos os registros de uma insercao feita em um estoque
    public static List<EstoqueHasProduto> getAllProductsFrom(int insercao, int estoque) {
        List insList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        insList = s.createQuery("FROM EstoqueHasProduto e WHERE e.insercao = '" + insercao + "' " +
                "and e.estoque = '" + estoque + "' ").list();
        s.getTransaction().commit();
        s.close();
        return insList;
    }

    //traz um registro pelo seu id
    public static EstoqueHasProduto getById(EstoqueHasProdutoId id) {
        EstoqueHasProduto e = new EstoqueHasProduto();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        e = (EstoqueHasProduto) s.createQuery("FROM EstoqueHasProduto e WHERE e.insercao = '" + id.getInsercaoId() + "' " +
                "and e.estoque = '" + id.getEstoqueId() + "' " +
                "and e.produto = '" + id.getProdutoId() + "'").getSingleResult();
        s.getTransaction().commit();
        s.close();
        return e;
    }

    //traz uma listagem formatada dos produtos inserido (utilizada para popular a tabela da tela ItensStockTab)
    public static List<Object[]> getinserts(String busca) {
        List<Object[]> insList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        insList = (List<Object[]>) s.createQuery("select distinct p.id, p.nome, m.nome, u.sigla, es.quantidade, es.valorunitario " +
                "from Produto p, EstoqueHasProduto es, Insercao i, Marca m, Unidade u " +
                "where p.id = es.produto and es.insercao = " + busca + " and p.marca = m.id and u.id = p.unidade and es.ativo = '1'").list();
        s.getTransaction().commit();
        s.close();

        return insList;
    }

    //desfaz as alteraçÕes feitas (utilizada na tela ItensStockTab)
    public static void rollback(int insercao, int estoque, List<Object[]> produtos) {

        EstoqueHasProdutoDAO epDao = new EstoqueHasProdutoDAO();

        List<EstoqueHasProduto> allEps = getAllProductsFrom(insercao, estoque);
        EstoqueHasProduto ep = new EstoqueHasProduto();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();

        //valida se ha uma lista de backup com os produtos salvos da ultima modificação nos registros
        if (produtos != null) {

            //apaga todos os registros
            for (EstoqueHasProduto e : allEps) {
                epDao.delete(e);
            }

            //salva os registros da tabela de backup, com as suas antigas propriedades
            for (Object[] p : produtos) {
                ep.setId(new EstoqueHasProdutoId(insercao, estoque, Integer.parseInt(p[0].toString())));
                ep.setEstoque(EstoqueDAO.getById(estoque));
                ep.setInsercao(InsercaoDAO.getById(insercao));
                ep.setProduto(ProdutoDAO.getById(Integer.parseInt(p[0].toString())));
                ep.setQuantidade(Double.parseDouble(p[4].toString()));
                ep.setValorunitario(Double.parseDouble(p[5].toString()));
                ep.setAtivo('1');

                epDao.add(ep);
            }

        } else {

            //se as modificações forma feitas em um novo registro, cancela a ação
            //e exclui a inserção (exclusão dos itens feita via trigger no banco)
            InsercaoDAO iDao = new InsercaoDAO();
            Insercao insert = iDao.getById(insercao);
            iDao.delete(insert);

        }

    }

    public static void updateProdOfInsert(int insert, int stock, int prod, EstoqueHasProduto estoqueHasProduto){
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.createQuery("update EstoqueHasProduto set produto = "+ estoqueHasProduto.getProduto().getId() +", " +
                    "quantidade = " + estoqueHasProduto.getQuantidade() + ", " +
                    "valorunitario = " + estoqueHasProduto.getValorunitario() + " where " +
                    "estoque = " + stock + " and "+
                    "insercao = " + insert + " and "+
                    "produto =  " + prod).executeUpdate();
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            Error.exception(e);
        }
    }

    public static void updateStockOfInsert(int insert, int stockOld, int stockNew){
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.createQuery("update EstoqueHasProduto set estoque = "+ stockNew +" where " +
                    "estoque = " + stockOld + " and "+
                    "insercao = " + insert).executeUpdate();
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            Error.exception(e);
        }
    }

}
