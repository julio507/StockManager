package com.yard.stockmanager.tabs;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.*;

public class CategorManagerTab extends parts.ManagementTab<Object>
{

    private Stage stage;

    public CategorManagerTab(){
        super("Gerenciamento de Catgoria");
        initComponents();
    }

    @Override
    public void refresh() {

    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void save() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void changeStatus() {

    }

    @Override
    public void select() {

    }

    @Override
    public void clear() {

    }

    public void initComponents(){
        innerGrid.addRow(0, labNome, tfdNome);
        innerGrid.addRow(1, labDescricao, tfdDescricao);
        //innerGrid.addRow(2, btnSalva);

        TableColumn<Object, Integer> id = new TableColumn<>("ID");
        TableColumn<Object, String> nome = new TableColumn<>("Nome");
        TableColumn<Object, String> descricao = new TableColumn<>("Descrição");

        id.setCellValueFactory(new PropertyValueFactory<Object, Integer>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<Object, String>("nome"));
        descricao.setCellValueFactory(new PropertyValueFactory<Object, String>("descrica"));

        tableView.getColumns().addAll(
                id,
                nome,
                descricao
        );

        refresh();
    }

    //Criação das variaveis
    private Label labNome = new Label("Nome");
    private Label labDescricao = new Label("Descrição");

    private TextField tfdNome = new TextField();
    private TextField tfdDescricao = new TextField();

    private Button btnSalva = new Button("Salvar");
    private Button btnEditar = new Button("Editar");
}
