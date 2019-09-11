/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.persistence.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author julio
 */
public class DBConnection
{

    private static DBConnection instancia = null;
    private Connection conexao = null;

    public DBConnection()
    {
        try
        {
            // Carrega informações do arquivo de propriedades
            Properties prop = new Properties();
            prop.load(new FileInputStream("db.properties"));
            String dbdriver = prop.getProperty("db.driver");
            String dburl = prop.getProperty("db.url");
            String dbuser = prop.getProperty("db.user");
            String dbsenha = prop.getProperty("db.senha");

            // Carrega Driver do Banco de Dados
            Class.forName(dbdriver);

            if (dbuser.length() != 0) // conexão COM usuário e senha
            {
                conexao = DriverManager.getConnection(dburl, dbuser, dbsenha);
            }
            else // conexão SEM usuário e senha
            {
                conexao = DriverManager.getConnection(dburl);
            }

        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    public static DBConnection getInstance()
    {
        if (instancia == null)
        {
            instancia = new DBConnection();
        }
        return instancia;
    }

    public Connection getConnection()
    {
        if (conexao == null)
        {
            throw new RuntimeException("conexao==null");
        }
        return conexao;
    }

    public void shutDown()
    {
        try
        {
            conexao.close();
            instancia = null;
            conexao = null;
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

}
