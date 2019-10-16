package com.yard.stockmanager.useful;

import javafx.scene.control.Alert;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Error {
    
    private static FileHandler handler = null;

    private static FileHandler getHandler()
    {
        try
        {
            if( handler == null )
            {
                handler = new FileHandler( "logs.log" );
            }
        }

        catch( Exception e )
        {
            e.printStackTrace();
        }

        return handler;
    }

    public static void message(String message )
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(message);
        a.setHeaderText(null);
        a.setTitle( "Aviso" );
        a.showAndWait();
    }

    public static void log( String message, Class c )
    {
        try
        {
            Logger logger = Logger.getLogger( c == null ? "message" : c.getName() );
            logger.addHandler( getHandler() );

            logger.log( Level.WARNING, message );
        }
        
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public static void messageAndLog( String message )
    {
        message( message );
        log( message, null );
    }

    public static void exception( Exception e )
    {
        message( "Uma excessão inesperada occoreu ao tentar executar a tarefa" );
        log( e.getMessage(), e.getClass() );
    }
}
