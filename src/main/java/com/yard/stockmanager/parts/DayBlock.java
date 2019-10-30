package com.yard.stockmanager.parts;

import java.time.LocalDate;

import com.yard.stockmanager.useful.DateFormat;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class DayBlock extends BorderPane {
    
    public enum DayWeek
    {
        SUN( "Domingo" ),
        MON( "Segunda" ),
        TUE( "Terça" ),
        WED( "Quarta" ),
        THU( "Quinta" ),
        FRI( "Sexta" ),
        SAT( "Sabado" );
        
        private final String name;

        DayWeek( String name )
        {
            this.name = name;
        }

        public String toString()
        {
            return name;
        }
    }

    public enum Month
    {
        JAN( "Janeiro" ),
        FEB( "Fevereiro" ),
        MAR( "Março" ),
        APR( "Abril" ),
        MAY( "Maio" ),
        JUN( "Junho" ),
        JUL( "Julho" ),
        AUG( "Agosto" ),
        SEP( "Setembro" ),
        OCT( "Outubro" ),
        NOV( "Novembro" ),
        DEC( "Dezembro" );
        
        private String name;

        Month( String name )
        {
            this.name = name;
        }

        public String toString()
        {
            return name;
        }
    }

    private LocalDate date;
    private Font font = new Font( 20 );

    public DayBlock(LocalDate date) {
        this.date = date;

        initComponents();

        refreshContent();
    }

    public void refreshContent() {
        field.setText( DateFormat.getDayString(date) );
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setOnClick( EventHandler<Event> event )
    {
        field.setOnMouseClicked( event );
    };

    private void initComponents() {
        field.setPrefHeight(75);
        field.setEditable(false);
        field.setFont( font );
        field.setAlignment( Pos.CENTER );
        setPrefHeight( 75 );
        setPrefWidth(75);

        setTop(field);
    }

    private TextField field = new TextField();
}