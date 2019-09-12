package com.yard.stockmanager.persistence.dao;

import java.util.List;

public interface Dao<T>
{
    public void add(T t);

    public List<T> get(int id );

    public void delete( int id );

    public void update( T t );
}
