/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Julio
 */
public abstract class QueryTab<T>
        extends
        Tab
{

    public QueryTab(String text)
    {
        super(text);
        initComponents();
    }

    public abstract void refreshContent();

    public abstract void print();

    private void initComponents()
    {
        table.setPrefHeight(720);
        
        defaultGrid.addRow(1, refreshButton, printButton);
        gridTop.addRow(0, customGrid);
        gridTop.addRow(1, defaultGrid);

        gridCenter.setPadding(new Insets(10));
        customGrid.setPadding(new Insets(10));
        defaultGrid.setPadding(new Insets(10));
        defaultGrid.setHgap(5);

        gridCenter.addRow(0, table);

        border.setCenter(gridCenter);
        border.setTop(gridTop);

        setContent(border);

        refreshButton.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event t)
            {
                refreshContent();
            }
        });

        printButton.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event t)
            {
                print();
            }
        });
    }

    protected TableView<T> table = new TableView();
    protected GridPane customGrid = new GridPane();
    protected GridPane defaultGrid = new GridPane();

    private Button refreshButton = new Button("Atualizar");
    protected Button printButton = new Button("Imprimir");

    private GridPane gridTop = new GridPane();
    protected GridPane gridCenter = new GridPane();
    private BorderPane border = new BorderPane();
}
