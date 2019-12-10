package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.TabMenuItem;
import com.yard.stockmanager.parts.Window;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.awt.*;

public class InsertReport {
    private Window report;
    private TabMenuItem item;

    public InsertReport(TabMenuItem item){
        this.item = item;
        initComponents();
    }

    private void initComponents() {

        report = new Window(item.getTab().getTabPane().getScene(), body, "Gerador de Relatórios");

    }

    //campos do relatório
    private Label labDataIni = new Label();
    private Label labDataFim = new Label();
    private TextField tfdDataIni = new TextField();
    private TextField tfdDatafim = new TextField();

    //grid Pane
    private GridPane field = new GridPane();
    private GridPane buttons = new GridPane();

    //buttons
    private Button btnGRel= new Button("Gerar relatório");
    private Button btnCancelar= new Button("Cancelar");

    //titulo
    private Text title = new Text("Relatório de Inserções de itens no estoque");
    private HBox body = new HBox(title, field, buttons);
}
