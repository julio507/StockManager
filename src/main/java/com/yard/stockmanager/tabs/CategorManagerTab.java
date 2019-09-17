package com.yard.stockmanager.tabs;

import com.yard.stockmanager.persistence.dao.CategoriaDAO;
import com.yard.stockmanager.persistence.entity.Categoria;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import parts.ManagementTab;

import java.util.List;

public class CategorManagerTab extends ManagementTab<Categoria>
{

    public CategorManagerTab(){
        super("Gerenciamento de Catgoria");
        initComponents();
    }

    @Override
    public void refresh() {
        CategoriaDAO dao = new CategoriaDAO();

        List<Categoria> list = dao.getAll();

        tableView.setItems(FXCollections.observableArrayList( list ));
        tableView.refresh();
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void save() {
        Categoria cat = new Categoria();

        cat.setNome(tfdNome.getText());
        cat.setDescricao(tfdDescricao.getText());

        CategoriaDAO estDao = new CategoriaDAO();

        estDao.add(cat);

        refresh();
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

        TableColumn<Categoria, Integer> id = new TableColumn<>("ID");
        TableColumn<Categoria, String> nome = new TableColumn<>("Nome");
        TableColumn<Categoria, String> descricao = new TableColumn<>("Descrição");

        id.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nome"));
        descricao.setCellValueFactory(new PropertyValueFactory<Categoria, String>("descricao"));

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
