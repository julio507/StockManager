package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO
        extends
            Dao<Categoria> {

    public List<Categoria> getAll()
    {
        List<Categoria> list = new ArrayList<>();

        try
        {

            String sql = "select * from categoria";

        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return list;
    }
}
