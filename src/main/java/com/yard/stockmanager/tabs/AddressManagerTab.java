package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.dao.EnderecoDAO;
import com.yard.stockmanager.persistence.entity.Endereco;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AddressManagerTab extends ManagementTab<Endereco> {
    private Endereco selected;

    public AddressManagerTab() {
        super("Gerenciamento de Endereços");
        initComponents();
    }

    @Override
    public void refresh() {
        EnderecoDAO dao = new EnderecoDAO();
        List<Endereco> list = dao.getAll();

        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void save() {
        Endereco end = new Endereco();

        end.setBairro(tfdBairro.getText());
        end.setCep(tfdCep.getText());
        end.setComplementos(tfdComplementos.getText());
        end.setNumero(tfdNumero.getText());
        end.setRua(tfdRua.getText());

        EnderecoDAO endDao = new EnderecoDAO();
        endDao.add(end);

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
        selected = (Endereco) getSelected();

        if (selected != null) {
            tfdBairro.setText(selected.getBairro());
            tfdCep.setText(selected.getCep());
            tfdComplementos.setText(selected.getComplementos());
            tfdNumero.setText(selected.getNumero());
            tfdRua.setText(selected.getRua());
            id = selected.getId();
        } else {
            clear();
        }

    }

    @Override
    public void clear() {

    }

    private void initComponents() {

        innerGrid.addRow(0, labRua, tfdRua);
        innerGrid.addRow(1, labNumero, tfdNumero);
        innerGrid.addRow(2, labBairro, tfdBairro);
        innerGrid.addRow(3, labCep, tfdCep);
        innerGrid.addRow(4, labComplementos, tfdComplementos);

        TableColumn<Endereco, Integer> id = new TableColumn<>("ID");
        TableColumn<Endereco, String> rua = new TableColumn<>("Rua");
        TableColumn<Endereco, Integer> numero = new TableColumn<>("Número");
        TableColumn<Endereco, String> bairro = new TableColumn<>("Bairro");
        TableColumn<Endereco, Integer> cep = new TableColumn<>("CEP");
        TableColumn<Endereco, String> complementos = new TableColumn<>("Complementos");

        id.setCellValueFactory(new PropertyValueFactory<Endereco, Integer>("id"));
        rua.setCellValueFactory(new PropertyValueFactory<Endereco, String>("rua"));
        numero.setCellValueFactory(new PropertyValueFactory<Endereco, Integer>("numero"));
        bairro.setCellValueFactory(new PropertyValueFactory<Endereco, String>("bairro"));
        cep.setCellValueFactory(new PropertyValueFactory<Endereco, Integer>("cep"));
        complementos.setCellValueFactory(new PropertyValueFactory<Endereco, String>("complementos"));

        tableView.getColumns().addAll(
                id,
                rua,
                numero,
                bairro,
                cep,
                complementos
        );

        refresh();

    }

    private int id;

    //Criação dos componentes da tela
    private Label labRua = new Label("Rua:");
    private Label labNumero = new Label("Número:");
    private Label labBairro = new Label("Bairro:");
    private Label labCep = new Label("CEP:");
    private Label labComplementos = new Label("Complementos:");

    private TextField tfdRua = new TextField();
    private TextField tfdNumero = new TextField();
    private TextField tfdBairro = new TextField();
    private TextField tfdCep = new TextField();
    private TextField tfdComplementos = new TextField();


}


