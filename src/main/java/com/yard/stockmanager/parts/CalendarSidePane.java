package com.yard.stockmanager.parts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.yard.stockmanager.persistence.entity.Agendamento;
import com.yard.stockmanager.useful.DateFormat;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class CalendarSidePane
    extends BorderPane
{

    private LocalDate date;
    private Font font = new Font( 25 );
    private List<Agendamento> pendencies = new ArrayList<Agendamento>();

    /**
     * 
     */
    public CalendarSidePane() {
        initComponents();
    }

    public void refreshContent()
    {
        if( date != null )
        {
            dateField.setText( DateFormat.getFormatedString(date) );

            list.setItems( FXCollections.observableArrayList( pendencies ) );
        }
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

        refreshContent();
    }

    /**
     * @return the pendencies
     */
    public List<Agendamento> getPendencies() {
        return pendencies;
    }

    /**
     * @param pendencies the pendencies to set
     */
    public void setPendencies(List<Agendamento> pendencies) {
        this.pendencies = pendencies;
    }

    private void initComponents()
    {
        dateField.setFont( font );
        dateField.setEditable( false );

        setTop( dateField );
        setCenter( list );
        setBottom( newButton );
    }

    private TextField dateField = new TextField();
    private ListView<Agendamento> list = new ListView<Agendamento>();

    private Button newButton = new Button( "Novo" );
}