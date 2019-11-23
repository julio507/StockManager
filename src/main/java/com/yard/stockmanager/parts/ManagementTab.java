/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.parts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author julio
 */
public abstract class ManagementTab<T>
        extends
        Tab
{

    private Object selected;
    private Object bottomSelected;

    private boolean printable = false;
    private boolean doubleAction = false;
    private Font font = new Font(20);
    protected int lastPage = 25;

    public ManagementTab(String label)
    {
        super(label);
        initComponents();
    }

    public ManagementTab(String label, Boolean doubleAction)
    {
        super(label);
        this.doubleAction = doubleAction;
        initComponents();
    }

    public abstract void refresh();

    public abstract boolean validate();

    public abstract void save();

    public abstract void edit();

    public abstract void changeStatus();

    public abstract void select();

    public abstract void clear();

    public void details(){};

    public void print(){};
    
    public void doPagination()
    {
        
    }

    public Object getSelected()
    {
        return selected;
    }

    public void setSelected(Object selected)
    {
        this.selected = selected;
    }

    //seleçoes da tabela inferior
    public Object getBottomSelected()
    {
        return bottomSelected;
    }

    public void setBottomSelected(Object bottomSelected)
    {
        this.bottomSelected = bottomSelected;
    }

    public String getFilter()
    {
        return searchField.getText();
    }

    public boolean isPrintable()
    {
        return printable;
    }

    public void setPrintable(boolean printable)
    {
        this.printable = printable;
        
        printButton.setDisable(!printable);
    }

    //metodos de edição das tabelas
    public void selectBottom(){}//seleção dos registros da tabela inferior

    public void editUpperRegister(){}

    public void removeUpperRegister(){}

    public void editBottomRegister(){}

    public void removeBottomRegister(){}

    //metodos para desabilitar os controles de edição das tabelas
    public void disableUpperButtons(){
        editButton.setDisable(true);
        removeButton.setDisable(true);
    }

    public void disableBottomButtons(){
        editBottomButton.setDisable(true);
        removeBottomButton.setDisable(true);
    }

    //metodos para habilitar os controles de edição das tabelas
    public void enableUpperButtons(){
        editButton.setDisable(false);
        removeButton.setDisable(false);
    }

    public void enableBottomButtons(){
        editBottomButton.setDisable(false);
        removeBottomButton.setDisable(false);
    }

    private void initComponents()
    {

        mark.setTextFill(new Color(1, 0, 0, 0));

        ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setPercentWidth(30);

        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setPercentWidth(70);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);

        searchGrid.addColumn(0, searchField);
        searchGrid.addColumn(1, searchButton);

        searchField.setPrefWidth(925);
        searchField.setFont(font);
        searchButton.setFont(font);

        searchGrid.setPrefWidth(Double.MAX_VALUE);

        outerGrid.getColumnConstraints().addAll(cons1, cons2);
        outerGrid.getRowConstraints().add(row1);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setEditable(false);
        outerGrid.setPadding(new Insets(10));

        tablePane.setTop(searchGrid);

        //duas tabelas
        if(!doubleAction) {
            tablePane.setCenter(tableView);
        }else{
            upperTableButtonsGrid.addRow(0, editButton, removeButton);
            bottomTableButtonsGrid.addRow(0, editBottomButton, removeBottomButton);

            tableViewBottom.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tableViewBottom.setEditable(false);
            tablesGrid.addColumn(0, tableView,upperTableButtonsGrid, tableViewBottom, bottomTableButtonsGrid);
            tablePane.setCenter(tablesGrid);
        }

        tablePane.setPadding(new Insets(10));
        innerGrid.setPadding(new Insets(10));

        outerGrid.addColumn(0, innerPane);
        outerGrid.addColumn(1, tablePane);

        innerPane.setCenter(innerGrid);
        innerPane.setBottom(bottomGrid);

        bottomGrid.add(need, 0, 0, 3, 1);
        bottomGrid.addRow(1, saveButton, newButton, disableButton, printButton);

        bottomGrid.setAlignment(Pos.CENTER);

        borderPane.setCenter(outerGrid);

        setContent(borderPane);

        printButton.setDisable(!printable);
        
        tableView.setOnScroll( new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
                if( event.getDeltaY() < 0.0 )
                {
                    doPagination();
                }
            }
        } );

        saveButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (validate())
                {
                    if (getSelected() == null)
                    {
                        save();
                        clear();
                    }
                    else
                    {
                        edit();
                    }

                    refresh();
                }
            }
        });

        disableButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (selected != null)
                {
                    changeStatus();
                }

                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Erro ao trocar o status");
                    alert.setHeaderText(null);
                    alert.setContentText("Nenhum item selecionado");

                    alert.showAndWait();
                }
            }
        });

        newButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                clear();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
                setSelected(newValue);

                select();
            }
        });

        //selecão da tabela inferior
        tableViewBottom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
                setBottomSelected(newValue);

                selectBottom();
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                refresh();
            }
        });
        
        printButton.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                print();
            }
        });

        editButton.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                editUpperRegister();
            }
        });

        removeButton.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                removeUpperRegister();
            }
        });

        editBottomButton.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                editBottomRegister();
            }
        });

        removeBottomButton.setOnAction( new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                removeBottomRegister();
            }
        });
    }

    protected Label mark = new Label("*");
    private Label need = new Label("* - Campo Obrigatório");

    private TextField searchField = new TextField();
    private Button searchButton = new Button("Procurar");
    private GridPane searchGrid = new GridPane();

    private Button saveButton = new Button("Salvar");
    private Button newButton = new Button("Limpar/Novo");
    private Button disableButton = new Button("Desabilitar/Habilitar");
    private Button printButton = new Button( "Imprimir" );

    private Button editButton = new Button("Editar");
    private Button removeButton = new Button("Remover");
    private Button editBottomButton = new Button("Editar");
    private Button removeBottomButton = new Button("Remover");

    protected GridPane innerGrid = new GridPane();
    protected TableView<T> tableView = new TableView<T>();
    protected TableView<T> tableViewBottom = new TableView<T>();

    private GridPane outerGrid = new GridPane();
    private GridPane bottomGrid = new GridPane();
    private GridPane tablesGrid = new GridPane();
    private GridPane upperTableButtonsGrid = new GridPane();
    private GridPane bottomTableButtonsGrid = new GridPane();
    private BorderPane tablePane = new BorderPane();
    private BorderPane borderPane = new BorderPane();
    private BorderPane innerPane = new BorderPane();
}
