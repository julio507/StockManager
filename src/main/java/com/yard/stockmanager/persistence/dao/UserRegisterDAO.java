package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Funcionario;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserRegisterDAO
    extends
    Dao<Funcionario> {
        public static List<Funcionario> getAll() {
        List list = new ArrayList();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        list = s.createQuery("select f.id, f.pessoa, f.login, f.senha, f.senhaemail, f.ativo, f.funcao FROM Funcionario f ").list();
        s.getTransaction().commit();
        s.close();
        return list;
    }
}
