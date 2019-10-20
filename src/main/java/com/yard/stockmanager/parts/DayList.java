package com.yard.stockmanager.parts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.SourceType;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class DayList extends BorderPane {
    
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
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DayList(LocalDate date) {
        super();
        this.date = date;

        initComponents();

        refreshContent();
    }

    public void refreshContent() {
        field.setText(date.format(formatter));
    }

    private void initComponents() {
        setTop(field);
        setMinHeight(100);

        field.setEditable(false);

        setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {

            }
        });
    }

    private TextField field = new TextField();
}