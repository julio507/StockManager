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
            Statement st = DBConnection.getInstance().getConnection().createStatement();

            String sql = "insert into categoria value ( default, '" + categoria.getNome() + "', '" + categoria.getDescricao() + "')";
            System.out.println(sql);

            st.execute(sql);
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public List<Categoria> get(int id) {
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
            Statement st = DBConnection.getInstance().getConnection().createStatement();

            String sql = "select * from categoria";
            ResultSet rs = st.executeQuery(sql);

            while ( rs.next() )
            {
                Categoria c = new Categoria();

                c.setId( rs.getInt(1) );

                c.setNome( rs.getString(2) );
                c.setDescricao( rs.getString(3) );

                list.add(c);
            }
        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return list;
    }
}
