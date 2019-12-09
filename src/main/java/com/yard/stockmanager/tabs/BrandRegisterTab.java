package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.dao.FuncionarioDAO;
import com.yard.stockmanager.persistence.dao.MarcaDAO;
import com.yard.stockmanager.persistence.entity.Marca;
import com.yard.stockmanager.useful.Current;
import com.yard.stockmanager.useful.Error;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class BrandRegisterTab extends ManagementTab<Marca> {
    private Marca selected;

    public BrandRegisterTab() {
        super("Cadastro de Marcas", Current.getUser(), FuncionarioDAO.getById(Current.getUser()).getNivelacesso(), "BrandRegisterTab");
        initComponents();
    }

    @Override
    public void refresh() {
        List<Marca> list = marcaDAO.getPagination( getFilter(), 0, lastPage );

        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();

    }

    @Override
    public boolean validate() {
        if (idField.getText().equals("Novo")
                && (tfdMarca.getText().trim().isEmpty() || tfdMarca.getText().trim().length() <= 3)) {
            Error.message("Erro ao Cadastrar. Verifique os dados Inseridos!");
            return false;
        } else if (!idField.getText().equals("Novo")
                && (tfdMarca.getText().trim().isEmpty() || tfdMarca.getText().length() <= 3)) {
            Error.message("Erro ao Editar. Verifique os dados Inseridos!");
            return false;
        }

        return true;
    }

    @Override
    public void save() {
        try {
            Marca m = new Marca();

            m.setNome(tfdMarca.getText());

            m.setDescricao(tarDescricao.getText());

            m.setAtivo('1');

            marcaDAO.add(m);
        }

        catch (Exception e) {
            Error.message(e.getMessage());
        }

    }

    @Override
    public void edit() {
        try {
            Marca m = (Marca) getSelected();

            m.setNome(tfdMarca.getText());
            m.setDescricao(tarDescricao.getText());

            marcaDAO.update(m);
        } catch (Exception e) {
            Error.message(e.getMessage());
        }

        refresh();

    }

    @Override
    public void changeStatus() {
        try {
            Marca m = (Marca) getSelected();
            if (m.getAtivo() == '1')
                m.setAtivo('0');
            else {
                m.setAtivo('1');
            }
            marcaDAO.update(m);

            refresh();
        }

        catch (Exception e) {
            Error.message(e.getMessage());
        }

    }

    @Override
    public void select() {
        selected = (Marca) getSelected();

        if (selected != null) {
            idField.setText(selected.getId() + "");
            tfdMarca.setText(selected.getNome());
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
        tfdMarca.setText("");
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
        tfdMarca.setEditable(true);
        tarDescricao.setEditable(true);
        tarDescricao.setPrefSize(200,100);

        // Colunas da tabela
        TableColumn<Marca, Integer> id = new TableColumn<>("ID");
        TableColumn<Marca, String> marca = new TableColumn<>("Marca");
        TableColumn<Marca, String> descricao = new TableColumn<>("Descrição");
        TableColumn<Marca, Character> atv = new TableColumn<>("Ativo");

        id.setCellValueFactory(new PropertyValueFactory<Marca, Integer>("id"));
        marca.setCellValueFactory(new PropertyValueFactory<Marca, String>("Nome"));
        descricao.setCellValueFactory(new PropertyValueFactory<Marca, String>("descricao"));
        atv.setCellValueFactory(new PropertyValueFactory<Marca, Character>("Ativo"));

        // Tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefSize(1000, 1000);
        tableView.getColumns().addAll(id, marca, descricao, atv);

        innerGrid.addRow(0, labid, idField);
        innerGrid.addRow(1, labMarca, tfdMarca);
        innerGrid.addRow(2, labDescricao, tarDescricao);

        refresh();

    }

    // Criação dos componentes da tela
    private Label labid = new Label("ID:");
    private Label labMarca = new Label("Marca*:");
    private Label labDescricao = new Label("Descrição:");

    private TextField idField = new TextField("Novo");
    private TextField tfdMarca = new TextField();
    private TextArea tarDescricao = new TextArea();

    // dao
    private MarcaDAO marcaDAO = new MarcaDAO();
}
