package com.yard.stockmanager.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;

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

        labEndereco.setFont(font);
        labEndereco.setPrefHeight(250);

        tfdEndereco.setEditable(true);
        tfdEndereco.setFont(font);
        tfdEndereco.setPrefHeight(150);
        tfdEndereco.setPrefWidth(250);


        tela.setPadding(new Insets(100));
        tela.addRow(0,labEndereco, tfdEndereco);
        setContent(tela);


    }



    private Label labEndereco = new Label("Endereço");
    private TextField tfdEndereco = new TextField();

    private Button button = new Button("Botão adbk  akj");

    private GridPane tela = new GridPane();
}
