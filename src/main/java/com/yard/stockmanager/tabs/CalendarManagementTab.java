package com.yard.stockmanager.tabs;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        QUA( "Quanrta" ),
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
        initCompoents();

        refreshContent();
    }

    private void refreshContent() {
        LocalDate localDate = LocalDate.now();
        
        for (int i = 1; i < localDate.lengthOfMonth(); i++) 
        {
            LocalDate date = localDate.withDayOfMonth(i);

            TextField field = new TextField();
            BorderPane pane = new BorderPane();
            
            field.setText( date.toString() );

            pane.setTop( field );

            grid.add(pane, date.get( ChronoField.ALIGNED_WEEK_OF_MONTH ), date.get( ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH ));
        }

        for (DayWeek d : DayWeek.values()) {
            TextField field = new TextField();

            field.setText( d.toString() );

            grid.add(field, d.ordinal(), 0);
        }

    }

    public void initCompoents() {
        borderpane.setCenter(grid);
        setContent(borderpane);
    }

    private GridPane grid = new GridPane();
    private BorderPane borderpane = new BorderPane();
}