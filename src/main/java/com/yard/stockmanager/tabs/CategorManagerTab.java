package com.yard.stockmanager.tabs;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CategorManagerTab extends parts.ManagementTab<Object>
{

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

}
