package com.yard.stockmanager.tabs;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.yard.stockmanager.panes.CalendarSidePane;
import com.yard.stockmanager.parts.DayBlock;
import com.yard.stockmanager.parts.DayBlock.DayWeek;
import com.yard.stockmanager.parts.DayBlock.Month;
import com.yard.stockmanager.persistence.dao.AgendamentoDAO;
import com.yard.stockmanager.persistence.entity.Agendamento;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CalendarManagementTab extends Tab {

    private LocalDate localDate = LocalDate.now();

    private List<Month> months = Arrays.asList(Month.values());

    private AgendamentoDAO dao = new AgendamentoDAO(); 

    public CalendarManagementTab() {
        super("Calendario");

        initCompoents();

        refreshContent();
    }

    private void refreshContent() {
        centerGrid.getChildren().clear();

        Calendar calendar = Calendar.getInstance();

        List<Agendamento> list = new ArrayList<>();

        for (int i = 1; i < localDate.lengthOfMonth() + 1; i++) {
            LocalDate date = localDate.withDayOfMonth(i);

            WeekFields week = WeekFields.of(Locale.getDefault());

            DayBlock block = new DayBlock(date);

            if( dao.getForDate(date).size() > 0 )
            {
                block.setItalic( true );
            }

            centerGrid.add(block, date.get(week.dayOfWeek()), date.get(week.weekOfWeekBasedYear()));

            block.setOnClick(new EventHandler<Event>() {

                @Override
                public void handle(Event event) {
                    sidePane.setDate( block.getDate() );
                }
            });
        }

        for (DayWeek d : DayWeek.values()) {
            TextField field = new TextField();
            field.setAlignment( Pos.CENTER );
            field.setPrefWidth(100);
            field.setText(d.toString());
            field.setEditable(false);
            centerGrid.add(field, d.ordinal() + 1, 0);
        }

        monthCombo.getSelectionModel().select(localDate.getMonthValue() - 1);
        yearCombo.getSelectionModel().select((Integer) localDate.getYear());
    }

    public void initCompoents() {

        for (int i = localDate.getYear() - 20; i < localDate.getYear(); i++) {
            yearCombo.getItems().add(i);
        }

        for (int i = localDate.getYear(); i < localDate.getYear() + 20; i++) {
            yearCombo.getItems().add(i);
        }

        monthCombo.getItems().addAll(months);

        topGrid.addRow(0, monthLabel, monthCombo, yearLabel, yearCombo);
        topGrid.setAlignment( Pos.CENTER );
        topGrid.setHgap( 20 );
        topGrid.setPrefHeight( 50 );

        centerGrid.setAlignment( Pos.CENTER );

        borderpane.setTop(topGrid);
        borderpane.setCenter(centerGrid);
        borderpane.setRight( sidePane );
        setContent(borderpane);

        monthCombo.valueProperty().addListener(new ChangeListener<Month>() {

            @Override
            public void changed(ObservableValue<? extends Month> observable, Month oldValue, Month newValue) {
                localDate = localDate.withMonth(newValue.ordinal() + 1);

                refreshContent();
            }

        });

        yearCombo.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                localDate = localDate.withYear(newValue);

                refreshContent();
            }

        });
    }

    private Label monthLabel = new Label("Mes:");
    private Label yearLabel = new Label("Ano:");

    private ComboBox<Month> monthCombo = new ComboBox<Month>();
    private ComboBox<Integer> yearCombo = new ComboBox<Integer>();

    private CalendarSidePane sidePane = new CalendarSidePane();

    private GridPane topGrid = new GridPane();

    private GridPane centerGrid = new GridPane();
    private BorderPane borderpane = new BorderPane();
}