package com.yard.stockmanager.persistence.hibernate;

import com.fasterxml.classmate.AnnotationConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory
{
    public static EntityManager getManager()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("StockManager");
        
        EntityManager manager = factory.createEntityManager();
        
        return manager;
    }
}
