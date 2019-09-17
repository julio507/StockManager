package com.yard.stockmanager.tabs;

import com.yard.stockmanager.persistence.dao.EstoqueDAO;
import com.yard.stockmanager.persistence.entity.Estoque;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import parts.ManagementTab;

import java.util.List;

public class StockManagerTab extends ManagementTab<Estoque>
{

    public StockManagerTab()
    {
        super("Gerenciamento de Estoque");
        initComponents();
    }

    @Override
    public void refresh() {
        EstoqueDAO dao = new EstoqueDAO();

        List<Estoque> list = dao.getAll();

        tableView.setItems(FXCollections.observableArrayList( list ));
        tableView.refresh();
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void save() {
        Estoque est = new Estoque();

        est.setNome(tfdNome.getText());
        est.setDescricao(tfdDescricao.getText());
        est.setTelefone(tfdTelefone.getText());

        EstoqueDAO estDao = new EstoqueDAO();

        estDao.add(est);
        
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


    private void initComponents()
    {

        innerGrid.addRow(0,labNome, tfdNome);
        innerGrid.addRow(1,labEndeeco, tfdEndereco);
        innerGrid.addRow(2,labDescricao, tfdDescricao);
        innerGrid.addRow(3,labTelefone, tfdTelefone);


        TableColumn<Estoque, Integer> id = new TableColumn<>("ID");
        TableColumn<Estoque, String> nome = new TableColumn<>("Nome");
        TableColumn<Estoque, String> endereco = new TableColumn<>("Endereço");
        TableColumn<Estoque, String> descricao = new TableColumn<>("Descrição");
        TableColumn<Estoque, Integer> telefone = new TableColumn<>("Telefone");

        id.setCellValueFactory(new PropertyValueFactory<Estoque, Integer>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<Estoque, String>("nome"));
        endereco.setCellValueFactory(new PropertyValueFactory<Estoque, String>("endereco"));
        descricao.setCellValueFactory(new PropertyValueFactory<Estoque, String>("descricao"));
        telefone.setCellValueFactory(new PropertyValueFactory<Estoque, Integer>("telefone"));

        tableView.getColumns().addAll(
            id,
            nome,
            endereco,
            descricao,
            telefone
        );

        refresh();
        
    }

    //Criação dos componentes da tela
    private Label labEndeeco = new Label("Rua:");
    private Label labNome = new Label("Nome:");
    private Label labDescricao = new Label("Descrição:");
    private Label labTelefone = new Label("Telefone:");

    private TextField tfdEndereco = new TextField();
    private TextField tfdNome = new TextField();
    private TextField tfdDescricao = new TextField();
    private TextField tfdTelefone = new TextField();

}
