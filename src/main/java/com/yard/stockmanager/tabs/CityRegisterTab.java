package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.dao.CidadeDAO;
import com.yard.stockmanager.persistence.entity.Cidade;
import com.yard.stockmanager.useful.Error;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CityRegisterTab extends ManagementTab<Cidade> {

    private Cidade selected;

    public CityRegisterTab() {
        super("Cadastro de Cidades");
        initComponents();
    }

    @Override
    public void refresh() {
        List<Cidade> list = cidDAO.getPagination( getFilter(), 0, lastPage );

        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();

    }

    @Override
    public boolean validate() {
        if (idField.getText().equals("Novo")
                && (tfdCidade.getText().trim().isEmpty() || tfdCidade.getText().trim().length() <= 3)) {
            Error.message("Erro ao Cadastrar. Verifique os dados Inseridos!");
            return false;
        } else if (!idField.getText().equals("Novo")
                && (tfdCidade.getText().trim().isEmpty() || tfdCidade.getText().length() <= 3)) {
            Error.message("Erro ao Editar. Verifique os dados Inseridos!");
            return false;
        }

        if (idField.getText().equals("Novo")
                && (tfdUF.getText().trim().isEmpty() || tfdUF.getText().trim().length() != 2)) {
            Error.message("Erro ao Cadastrar. Verifique os dados Inseridos!");
            return false;
        } else if (!idField.getText().equals("Novo")
                && (tfdUF.getText().trim().isEmpty() || tfdUF.getText().length() != 2)) {
            Error.message("Erro ao Editar. Verifique os dados Inseridos!");
            return false;
        }

        return true;
    }

    @Override
    public void save() {
        try {
            Cidade c = new Cidade();

            c.setNome(tfdCidade.getText());

            c.setUf(tfdUF.getText());

            c.setAtivo('1');

            cidDAO.add(c);
        }

        catch (Exception e) {
            Error.message(e.getMessage());
        }

    }

    @Override
    public void edit() {
        try {
            Cidade c = (Cidade) getSelected();

            c.setNome(tfdCidade.getText());
            c.setUf(tfdUF.getText());

            cidDAO.update(c);
        } catch (Exception e) {
            Error.message(e.getMessage());
        }

        refresh();

    }

    @Override
    public void changeStatus() {
        try {
            Cidade c = (Cidade) getSelected();
            if (c.getAtivo() == '1')
                c.setAtivo('0');
            else {
                c.setAtivo('1');
            }
            cidDAO.update(c);

            refresh();
        }

        catch (Exception e) {
            Error.message(e.getMessage());
        }

    }

    @Override
    public void select() {
        selected = (Cidade) getSelected();

        if (selected != null) {
            idField.setText(selected.getId() + "");
            tfdCidade.setText(selected.getNome());
            tfdUF.setText(selected.getUf());
        }

        else {
            clear();
        }
    }

    @Override
    public void clear() {
        setSelected(null);

        idField.setText("Novo");
        tfdCidade.setText("");
        tfdUF.setText("");
    }

    @Override
    public void doPagination() {
        lastPage++;
        refresh();
    }

    private void initComponents() {
        // TextFields
        idField.setDisable(true);
        tfdCidade.setEditable(true);
        tfdUF.setEditable(true);

        // Colunas da tabela
        TableColumn<Cidade, Integer> id = new TableColumn<>("ID");
        TableColumn<Cidade, String> cidade = new TableColumn<>("Cidade");
        TableColumn<Cidade, String> uf = new TableColumn<>("UF");
        TableColumn<Cidade, Character> atv = new TableColumn<>("Ativo");

        id.setCellValueFactory(new PropertyValueFactory<Cidade, Integer>("id"));
        cidade.setCellValueFactory(new PropertyValueFactory<Cidade, String>("Nome"));
        uf.setCellValueFactory(new PropertyValueFactory<Cidade, String>("Uf"));
        atv.setCellValueFactory(new PropertyValueFactory<Cidade, Character>("Ativo"));

        // Tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefSize(1000, 1000);
        tableView.getColumns().addAll(id, cidade, uf, atv);

        innerGrid.addRow(0, labid, idField);
        innerGrid.addRow(1, labCidade, tfdCidade);
        innerGrid.addRow(2, labUF, tfdUF);

        refresh();

    }

    // Criação dos componentes da tela
    private Label labid = new Label("ID:");
    private Label labCidade = new Label("Cidade:");
    private Label labUF = new Label("UF:");

    private TextField idField = new TextField("Novo");
    private TextField tfdCidade = new TextField();
    private TextField tfdUF = new TextField();

    // dao
    private CidadeDAO cidDAO = new CidadeDAO();
}
