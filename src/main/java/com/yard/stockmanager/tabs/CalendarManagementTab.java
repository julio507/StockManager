package com.yard.stockmanager.tabs;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import com.yard.stockmanager.parts.DayList;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CalendarManagementTab extends Tab {

    private enum DayWeek
    {
        DOM( "Domingo" ),
        SEG( "Segunda" ),
        TER( "Terça" ),
        QUA( "Quarta" ),
        QUI( "Quinta" ),
        SEX( "Sexta" ),
        SAB( "Sabado" );
        
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

    public CalendarManagementTab() {
        super("Calendario");
        
        initCompoents();

        refreshContent();
    }

    private void refreshContent() {
        
        LocalDate localDate = LocalDate.now();

        for (int i = 1; i < localDate.lengthOfMonth() + 1; i++) 
        {
            LocalDate date = localDate.withDayOfMonth( i );

            WeekFields week = WeekFields.of( Locale.getDefault() );

            centerGrid.add( new DayList( date ), date.get( week.dayOfWeek() ), date.get( week.weekOfWeekBasedYear() ) );
        }

        for (DayWeek d : DayWeek.values()) {
            TextField field = new TextField();

            field.setText( d.toString() );
            field.setEditable( false );
            centerGrid.add(field, d.ordinal() + 1, 0);
        }
    }

    public void initCompoents() {
        centerGrid.setGridLinesVisible(true);

        borderpane.setTop( topGrid );
        borderpane.setCenter(centerGrid);
        setContent(borderpane);
    }

    private Label monthLabel = new Label( "Mes" );
    private Label yearLabel = new Label( "Ano" );

    private GridPane topGrid = new GridPane();
    
    private GridPane centerGrid = new GridPane();
    private BorderPane borderpane = new BorderPane();
}