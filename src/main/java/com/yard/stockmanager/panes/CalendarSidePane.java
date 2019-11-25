package com.yard.stockmanager.panes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.yard.stockmanager.parts.Window;
import com.yard.stockmanager.persistence.dao.AgendamentoDAO;
import com.yard.stockmanager.persistence.entity.Agendamento;
import com.yard.stockmanager.persistence.entity.AgendamentoId;
import com.yard.stockmanager.useful.Current;
import com.yard.stockmanager.useful.DateFormat;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class CalendarSidePane extends BorderPane {

    private LocalDate date;
    private Font font = new Font(25);
    private List<Agendamento> pendencies = new ArrayList<Agendamento>();

    private AgendamentoDAO dao = new AgendamentoDAO();

    /**
     * 
     */
    public CalendarSidePane() {
        initComponents();
    }

    public void refreshContent() {
        if (date != null) {
            dateField.setText(DateFormat.getFormatedString(date));

            pendencies = dao.getForDate(date);

            list.setItems(FXCollections.observableArrayList(pendencies));
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

    private void openForm(Agendamento a) {
        PendencyFormPane form = new PendencyFormPane();

        form.setSource(a);

        Window window = new Window(getScene(), form, "Formulario De Pendência");

        window.showAndWait();
    }

    private void initComponents() {
        dateField.setFont(font);
        dateField.setEditable(false);
        dateField.setAlignment(Pos.CENTER);

        southBox.getChildren().addAll(newButton, editButton, removeButton);

        setTop(dateField);
        setCenter(list);
        setBottom(southBox);

        newButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Agendamento a = new Agendamento();
                a.setData(DateFormat.toDate(date));
                a.setId(new AgendamentoId());
                a.getId().setFuncionarioId(Current.getUser());

                openForm(a);
            }
        });

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Agendamento a = list.getSelectionModel().getSelectedItem();

                openForm(a);
            }
        } );

        removeButton.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
    }

    private TextField dateField = new TextField();
    private ListView<Agendamento> list = new ListView<Agendamento>();

    private HBox southBox = new HBox();

    private Button newButton = new Button("Novo");
    private Button editButton = new Button("Editar");
    private Button removeButton = new Button("Remover");
}