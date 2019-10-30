package com.yard.stockmanager.panes;

import com.yard.stockmanager.persistence.dao.AgendamentoDAO;
import com.yard.stockmanager.persistence.entity.Agendamento;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PendencyFormPane extends BorderPane {

    private AgendamentoDAO dao = new AgendamentoDAO();
    private Agendamento source = new Agendamento();

    /**
     * 
     */
    public PendencyFormPane() {
        initComponents();
    }

    /**
     * @return the source
     */
    public Agendamento getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(Agendamento source) {
        this.source = source;
    }

    private boolean validate()
    {
        return true;
    }

    private void doAction()
    {
        dao.add( source );
    }

    private void initComponents() {
        dateField.setEditable( false );

        okButton.setAlignment(Pos.CENTER);
        grid.setAlignment(Pos.CENTER);

        hboxDateTime.getChildren().addAll( dateField, timeLabel, timeField);

        hboxBottom.getChildren().addAll(okButton, cancelButton);
        hboxBottom.setAlignment(Pos.CENTER);
        hboxBottom.setPadding(new Insets(10));

        grid.add( dateLabel, 0, 0, 1, 1 );
        grid.add( hboxDateTime, 1, 0, 2, 1 );
        
        grid.addRow(1, titleLabel, titleField);
        grid.addRow(2, descriptionLabel, descriptionField);

        setCenter(grid);
        setBottom(hboxBottom);

        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if( validate() )
                {
                    doAction();
                }
            }
            
        } );

        cancelButton.setOnAction( new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) getScene().getWindow();
                stage.close();
            }
        } );

    }

    private Button okButton = new Button( "Salvar" );
    private Button cancelButton = new Button( "Cancelar" );

    private Label dateLabel = new Label( "Data: " );
    private Label timeLabel = new Label( "Hora: " );
    private Label titleLabel = new Label( "Título :" );
    private Label descriptionLabel = new Label( "Descrição :" );

    private TextField dateField = new TextField();
    private TextField timeField = new TextField();
    private TextField titleField = new TextField();
    private TextArea descriptionField = new TextArea();

    private HBox hboxDateTime = new HBox();
    private HBox hboxBottom = new HBox();
    private GridPane grid = new GridPane();
}