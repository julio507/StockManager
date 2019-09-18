package com.yard.stockmanager.persistence.dao;

import com.yard.stockmanager.persistence.entity.Categoria;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO
        implements
            Dao<Categoria> {

    @Override
    public void add(Categoria categoria) {
        try
        {

            String sql = "insert into categoria value ( default, '" + categoria.getNome() + "', '" + categoria.getDescricao() + "')";
            System.out.println(sql);
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public Categoria get(int id) {
        return null;
    }


    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Categoria categoria) {

    }
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
