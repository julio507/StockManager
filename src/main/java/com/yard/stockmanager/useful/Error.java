package com.yard.stockmanager.useful;

import javafx.scene.control.Alert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Error {

    public static void message(String message )
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(message);
        a.setHeaderText(null);
        a.setTitle( "Aviso" );
        a.showAndWait();
    }
    
    public static void log( String message )
    {
        try
        {
            System.out.println( message );

            Date d = new Date();
            DateFormat df = new SimpleDateFormat( "dd-MM-yyyy" );

            FileWriter fw = new FileWriter( "/logs/" + df.format(d) + "-log.txt" );

            BufferedWriter bw = new BufferedWriter( fw );

            bw.write( message );

            bw.close();
        }
        
        catch( Exception e )
        {
            exception( e );
        }
    }
    
    public static void exception( Exception e )
    {
        message( "Uma excessão occoreu ao tentar executar a tarefa" );
        log( e.getMessage() );
    }
}
