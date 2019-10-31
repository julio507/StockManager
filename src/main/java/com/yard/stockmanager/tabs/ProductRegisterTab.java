package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.dao.CategoriaDAO;
import com.yard.stockmanager.persistence.dao.DepartmentDAO;
import com.yard.stockmanager.persistence.dao.MarcaDAO;
import com.yard.stockmanager.persistence.dao.UnidadeDAO;
import com.yard.stockmanager.persistence.entity.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;


public class ProductRegisterTab extends ManagementTab<Produto> {

    private List<Marca> marca = MarcaDAO.getAll();
    private List<Categoria> categoria = CategoriaDAO.getAll();
    private List<Departamento> departamento = DepartmentDAO.getAll();
    private List<Unidade> unidade = UnidadeDAO.getAll();

    public ProductRegisterTab() {
        super("Cadastro de Produtos");
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

    private void initComponents() {
        // TextFields
        idField.setDisable(true);

        marcaCombo.getItems().addAll(marca);
        categoriaCombo.getItems().addAll(categoria);
        departamentoCombo.getItems().addAll(departamento);
        unidadeCombo.getItems().addAll(unidade);


        // Colunas da tabela
        TableColumn<Produto, Integer> id = new TableColumn<>("ID");
        TableColumn<Produto, String> marca = new TableColumn<>("Marca");
        TableColumn<Produto, String> cat = new TableColumn<>("Categoria");
        TableColumn<Produto, String> dep = new TableColumn<>("Departamento");
        TableColumn<Produto, String> uni = new TableColumn<>("Unidade");
        TableColumn<Produto, String> prod = new TableColumn<>("Produto");
        TableColumn<Produto, String> desc = new TableColumn<>("Descrição");
        TableColumn<Produto, Double> valor = new TableColumn<>("Valor");
        TableColumn<Produto, Character> ativo = new TableColumn<>("Ativo");

        id.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("id"));
        marca.setCellValueFactory(new PropertyValueFactory<Produto, String>("marca"));
        cat.setCellValueFactory(new PropertyValueFactory<Produto, String>("categoria"));
        dep.setCellValueFactory(new PropertyValueFactory<Produto, String>("departamento"));
        uni.setCellValueFactory(new PropertyValueFactory<Produto, String>("unidade"));
        prod.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        desc.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
        valor.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valorunitario"));
        ativo.setCellValueFactory(new PropertyValueFactory<Produto, Character>("ativo"));

        // Tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(id, marca, cat, dep, uni, prod, desc, valor, ativo);

        innerGrid.addRow(0, idLabel, idField);
        innerGrid.addRow(1, marcaLabel, marcaCombo);
        innerGrid.addRow(2, catLabel, categoriaCombo);
        innerGrid.addRow(3, depLabel, departamentoCombo);
        innerGrid.addRow(4, uniLabel, unidadeCombo);
        innerGrid.addRow(5, prodLabel, prodField);
        innerGrid.addRow(6, descLabel, descricaoTar);
        innerGrid.addRow(7, valorLable, valorField);

        refresh();

    }

    //labels
    private Label idLabel = new Label("ID:");
    private Label marcaLabel = new Label("Marca:");
    private Label catLabel = new Label("Categoria:");
    private Label depLabel = new Label("Departamento:");
    private Label uniLabel = new Label("Unidade:");
    private Label prodLabel = new Label("Produto:");
    private Label descLabel = new Label("Descrição:");
    private Label valorLable = new Label("Custo:");


    //combos
    private ComboBox<Marca> marcaCombo = new ComboBox<Marca>();
    private ComboBox<Categoria> categoriaCombo = new ComboBox<Categoria>();
    private ComboBox<Departamento> departamentoCombo = new ComboBox<Departamento>();
    private ComboBox<Unidade> unidadeCombo = new ComboBox<Unidade>();

    //fields
    private TextField idField = new TextField("Novo");
    private TextField prodField = new TextField();
    private TextArea descricaoTar = new TextArea();
    private TextField valorField = new TextField();

}


