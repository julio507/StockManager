package com.yard.stockmanager.persistence.dao;


import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.entity.Estoque;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO
        implements
            Dao<Estoque>
{
    @Override
    public void add(Estoque estoque)
    {
        try
        {
            Statement st = DBConnection.getInstance().getConnection().createStatement();

            String sql = "insert into estoque value ( default, 1, " + estoque.getNome() + "," + estoque.getDescricao() + "," + estoque.getTelefone() + ")";
            System.out.println(sql);
            
            st.execute(sql);
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public List<Estoque> get(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    public List<Estoque> getAll()
    {
        List<Estoque> list = new ArrayList<>();

        try
        {
            Statement st = DBConnection.getInstance().getConnection().createStatement();

            String sql = "select * from estoque";
            ResultSet rs = st.executeQuery(sql);

            while ( rs.next() )
            {
                Estoque e = new Estoque();

                Endereco en = new Endereco();

                e.setId( rs.getInt(1) );

                en.setId( rs.getInt(2) );
                e.setEnderecoid( en );
                e.setNome( rs.getString(3) );
                e.setDescricao( rs.getString(4) );
                e.setTelefone( rs.getString( 5 ) );

                list.add(e);
            }
        }

        catch (Exception e)
        {
            System.out.println(e);
        }

        return list;
    }
}
