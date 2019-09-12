/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

/**
 *
 * @author julio
 */
public abstract class ManagementTab<T>
        extends
        Tab
{

    private Object selected;

    private boolean printable = false;
    
    public ManagementTab(String label)
    {
        super(label);
        initComponents();
    }

    public abstract void refresh();

    public abstract boolean validate();

    public abstract void save();

    public abstract void edit();

    public abstract void changeStatus();

    public abstract void select();

    public abstract void clear();

    public void print(){};
    
    public Object getSelected()
    {
        return selected;
    }

    public void setSelected(Object selected)
    {
        this.selected = selected;
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

    private void initComponents()
    {
        mark.setTextFill(new Color(1, 0, 0, 0));

        ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setPercentWidth(75);

        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setPercentWidth(25);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);

        searchGrid.addColumn(0, searchField);
        searchGrid.addColumn(1, searchButton);

        searchField.setPrefWidth(925);

        searchGrid.setPrefWidth(Double.MAX_VALUE);

        outerGrid.getColumnConstraints().addAll(cons1, cons2);
        outerGrid.getRowConstraints().add(row1);

        tableView.setEditable(false);
        outerGrid.setPadding(new Insets(10));

        tablePane.setTop(searchGrid);
        tablePane.setCenter(tableView);
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
        
        saveButton.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                if (validate())
                {
/*                    if (getSelected() == null)
                    {
                        save();
                        clear();
                    }
                    else
                    {
                        edit();
                    }*/

                    save();
                    clear();
                    
                    refresh();
                }
            }
        });

        disableButton.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event)
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

        newButton.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                clear();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
                setSelected(newValue);

                select();
            }
        });

        searchButton.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                refresh();
            }
        });
        
        printButton.setOnAction( new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                print();
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

    protected GridPane innerGrid = new GridPane();
    protected TableView<T> tableView = new TableView();

    private GridPane outerGrid = new GridPane();
    private GridPane bottomGrid = new GridPane();
    private BorderPane tablePane = new BorderPane();
    private BorderPane borderPane = new BorderPane();
    private BorderPane innerPane = new BorderPane();
}
