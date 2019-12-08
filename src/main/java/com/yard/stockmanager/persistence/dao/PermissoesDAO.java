package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Permissoes;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PermissoesDAO extends Dao<Permissoes> {

    public List<Permissoes> getPermissions(int userId, String tab){
        List insList = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        insList = s.createQuery("FROM Permissoes").list();
        s.getTransaction().commit();
        s.close();
        return insList;

    }

    /**
     * Busca na base de dados a permissão(regra) para o usuário(usuario), na tela(tab),
     * retornando um um objeto da classe Permissoes caso haja alguma permissão,
     * ou null caso nada seja encontrado.
     *
     * @param userId
     * @param tab
     * @param regra
     * @return
     */
    public static Permissoes hasPermission(int userId, String tab, String regra){
        List<Permissoes> result;
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        result = s.createQuery("FROM Permissoes where regra like '%"+ tab +"%' and regra like '%"+ regra +"%' and funcionario = " + userId).getResultList();
        s.getTransaction().commit();
        s.close();

        if(result.size() > 0) {
            return result.get(0);
        }else{
            return null;
        }
    }
}


