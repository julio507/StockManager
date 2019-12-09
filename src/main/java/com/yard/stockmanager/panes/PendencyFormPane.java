package com.yard.stockmanager.panes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import com.yard.stockmanager.parts.MaskTextField;
import com.yard.stockmanager.persistence.dao.AgendamentoDAO;
import com.yard.stockmanager.persistence.entity.Agendamento;
import com.yard.stockmanager.persistence.entity.PessoaHasAgendamento;
import com.yard.stockmanager.useful.DateFormat;

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
import javafx.util.Callback;

public class PendencyFormPane extends BorderPane {

    private AgendamentoDAO dao = new AgendamentoDAO();
    private Agendamento source = new Agendamento();

    private Callback<Agendamento,Agendamento> task;

    /**
     * 
     */
    public PendencyFormPane( Callback<Agendamento,Agendamento> task ) {
        this.task = task;

        initComponents();
    }

    public void refreshContent()
    {
        titleField.setText( source.getTitulo() );
        dateField.setText( DateFormat.getFormatedString( source.getData() ) );
        timeField.setText( DateFormat.getFormatedTime( source.getData() ) );

        descriptionField.setText( source.getDescricao() );
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
        refreshContent();
    }

    private boolean validate()
    {
        return true;
    }

    private Agendamento getInput()
    {
        Agendamento result = source;

        Calendar cal = Calendar.getInstance();
        cal.setTime( result.getData() );

        String time = timeField.getText();

        int hour = Integer.parseInt( time.split( ":" )[0] );
        int minute = Integer.parseInt( time.split( ":" )[1] );

        cal.set(Calendar.HOUR_OF_DAY, hour );
        cal.set(Calendar.MINUTE, minute );

        result.setData( cal.getTime() );
        result.setDescricao(descriptionField.getText());
        result.setTitulo( titleField.getText() );
        result.setPessoaHasAgendamentos( new HashSet<PessoaHasAgendamento>( new ArrayList<PessoaHasAgendamento>() ) );
        result.setAtivo('1');
    
        return result;
    }

    private void doAction()
    {
        if( validate() )
        {
            Agendamento a = getInput();

            if( a.getId().getId() == 0 )
            {
                dao.add( a );
            }

            else
            {
                dao.update( a );
            }
            task.call( a );
        }
    }

    private void close()
    {
        Stage stage = (Stage) getScene().getWindow();
        stage.close();
    }

    private void initComponents() {

        dateField.setEditable( false );

        okButton.setAlignment(Pos.CENTER);
        grid.setAlignment(Pos.CENTER);

        hboxBottom.getChildren().addAll(okButton, cancelButton);
        hboxBottom.setAlignment(Pos.CENTER);
        hboxBottom.setPadding(new Insets(10));
        
        grid.addRow(0, titleLabel, titleField);
        grid.addRow(1, dateLabel, dateField);
        //grid.addRow(2, timeLabel, timeField);
        grid.addRow(3, descriptionLabel, descriptionField);
        
        setCenter(grid);
        setBottom(hboxBottom);

        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if( validate() )
                {
                    doAction();
                    close();
                }
            }
        } );

        cancelButton.setOnAction( new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent event) {
                close();
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
    private TextField timeField = new TextField( "00:00" );
    private TextField titleField = new TextField();
    private TextArea descriptionField = new TextArea();

    private HBox hboxBottom = new HBox();
    private GridPane grid = new GridPane();
}