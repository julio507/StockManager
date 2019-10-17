package com.yard.stockmanager.parts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.SourceType;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class DayList extends BorderPane {
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