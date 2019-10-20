package com.yard.stockmanager.tabs;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.yard.stockmanager.parts.DayList;
import com.yard.stockmanager.parts.DayList.DayWeek;
import com.yard.stockmanager.parts.DayList.Month;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CalendarManagementTab extends Tab {

    private LocalDate localDate = LocalDate.now();

    private List<Month> months = Arrays.asList(Month.values());

    public CalendarManagementTab() {
        super("Calendario");

        initCompoents();

        refreshContent();
    }

    private void refreshContent() {
        centerGrid.getChildren().clear();

        for (int i = 1; i < localDate.lengthOfMonth() + 1; i++) {
            LocalDate date = localDate.withDayOfMonth(i);

            WeekFields week = WeekFields.of(Locale.getDefault());

            centerGrid.add(new DayList(date), date.get(week.dayOfWeek()), date.get(week.weekOfWeekBasedYear()));
        }

        for (DayWeek d : DayWeek.values()) {
            TextField field = new TextField();

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

        borderpane.setTop(topGrid);
        borderpane.setCenter(centerGrid);
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

    private ComboBox monthCombo = new ComboBox<Month>();
    private ComboBox yearCombo = new ComboBox<Integer>();

    private GridPane topGrid = new GridPane();

    private GridPane centerGrid = new GridPane();
    private BorderPane borderpane = new BorderPane();
}