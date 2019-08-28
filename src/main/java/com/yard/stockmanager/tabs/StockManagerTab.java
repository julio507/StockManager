package com.yard.stockmanager.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.awt.*;

public class StockManagerTab extends Tab
{

    private Stage stage;
    private Font font = new Font(24);

    public StockManagerTab()
    {
        super("Gerenciamento de Estoque");
        initComponents();
    }

    public void setStage(Stage s)
    {
        this.stage = s;
    }
    public Stage getStage()
    {
        return stage;
    }

    private void initComponents()
    {

        xlabel.setFont(font);
        xlabel.setPrefHeight(250);
        button.setPrefSize(100, 30);
        button.setPadding(new Insets(5));
        button.setVisible(true);

        tela.setPadding(new Insets(100));
        tela.addRow(0,xlabel,button);
        setContent(tela);


    }



    private Label xlabel = new Label("Xaidhsniajdsaijndiasj");

    private Button button = new Button("Botão adbk  akj");

    private GridPane tela = new GridPane();
}
