package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.dao.UnidadeDAO;
import com.yard.stockmanager.persistence.entity.Unidade;
import com.yard.stockmanager.useful.Error;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UnityRegisterTab extends ManagementTab<Unidade> {

    private Unidade selected;

    public UnityRegisterTab() {
        super("Cadastro de Unidades");
        initComponents();
    }

    @Override
    public void refresh() {
        List<Unidade> list = unidadeDAO.getPagination( getFilter(), 0, lastPage );

        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();

    }

    @Override
    public boolean validate() {
        if (idField.getText().equals("Novo")
                && (tfdUnidade.getText().trim().isEmpty() || tfdUnidade.getText().trim().length() <= 3)) {
            Error.message("Erro ao Cadastrar. Verifique os dados Inseridos!");
            return false;
        } else if (!idField.getText().equals("Novo")
                && (tfdUnidade.getText().trim().isEmpty() || tfdUnidade.getText().length() <= 3)) {
            Error.message("Erro ao Editar. Verifique os dados Inseridos!");
            return false;
        }

        return true;
    }

    @Override
    public void save() {
        try {
            Unidade u = new Unidade();

            u.setNome(tfdUnidade.getText());

            u.setDescricao(tarDescricao.getText());

            u.setAtivo('1');

            unidadeDAO.add(u);
        }

        catch (Exception e) {
            Error.message(e.getMessage());
        }

    }

    @Override
    public void edit() {
        try {
            Unidade u = (Unidade) getSelected();

            u.setNome(tfdUnidade.getText());
            u.setDescricao(tarDescricao.getText());

            unidadeDAO.update(u);
        } catch (Exception e) {
            Error.message(e.getMessage());
        }

        refresh();

    }

    @Override
    public void changeStatus() {
        try {
            Unidade u = (Unidade) getSelected();
            if (u.getAtivo() == '1')
                u.setAtivo('0');
            else {
                u.setAtivo('1');
            }
            unidadeDAO.update(u);

            refresh();
        }

        catch (Exception e) {
            Error.message(e.getMessage());
        }

    }

    @Override
    public void select() {
        selected = (Unidade) getSelected();

        if (selected != null) {
            idField.setText(selected.getId() + "");
            tfdUnidade.setText(selected.getNome());
            tarDescricao.setText(selected.getDescricao());
        }

        else {
            clear();
        }
    }

    @Override
    public void clear() {
        setSelected(null);

        idField.setText("Novo");
        tfdUnidade.setText("");
        tarDescricao.setText("");
    }

    @Override
    public void doPagination() {
        lastPage++;
        refresh();
    }

    private void initComponents() {
        // TextFields
        idField.setDisable(true);
        tfdUnidade.setEditable(true);
        tarDescricao.setEditable(true);
        tarDescricao.setPrefSize(200,100);

        // Colunas da tabela
        TableColumn<Unidade, Integer> id = new TableColumn<>("ID");
        TableColumn<Unidade, String> unidade = new TableColumn<>("Unidade");
        TableColumn<Unidade, String> descricao = new TableColumn<>("Descrição");
        TableColumn<Unidade, Character> atv = new TableColumn<>("Ativo");

        id.setCellValueFactory(new PropertyValueFactory<Unidade, Integer>("id"));
        unidade.setCellValueFactory(new PropertyValueFactory<Unidade, String>("Nome"));
        descricao.setCellValueFactory(new PropertyValueFactory<Unidade, String>("descricao"));
        atv.setCellValueFactory(new PropertyValueFactory<Unidade, Character>("Ativo"));

        // Tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefSize(1000, 1000);
        tableView.getColumns().addAll(id, unidade, descricao, atv);

        innerGrid.addRow(0, labid, idField);
        innerGrid.addRow(1, labMarca, tfdUnidade);
        innerGrid.addRow(2, labDescricao, tarDescricao);

        refresh();

    }

    // Criação dos componentes da tela
    private Label labid = new Label("ID:");
    private Label labMarca = new Label("Unidade*:");
    private Label labDescricao = new Label("Descrição:");

    private TextField idField = new TextField("Novo");
    private TextField tfdUnidade = new TextField();
    private TextArea tarDescricao = new TextArea();

    // dao
    private UnidadeDAO unidadeDAO = new UnidadeDAO();
}
