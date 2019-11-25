package com.yard.stockmanager.tabs;

import com.yard.stockmanager.useful.CellFormat;
import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.parts.MaskTextField;
import com.yard.stockmanager.persistence.dao.*;
import com.yard.stockmanager.persistence.entity.*;
import com.yard.stockmanager.useful.Error;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;


public class ProductRegisterTab extends ManagementTab<Produto> {

    private List<Marca> marca = MarcaDAO.getAll();
    private List<Categoria> categoria = CategoriaDAO.getAll();
    private List<Departamento> departamento = DepartmentDAO.getAll();
    private List<Unidade> unidade = UnidadeDAO.getAll();

    private Produto selected;

    public ProductRegisterTab() {
        super("Cadastro de Produtos");
        initComponents();
    }

    @Override
    public void refresh() {
        List<Produto> list = prDao.getAll(getFilter());

        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();

    }

    @Override
    public boolean validate() {

        //validação das Combos
        if (marcaCombo.getSelectionModel().isEmpty() || categoriaCombo.getSelectionModel().isEmpty()
                || departamentoCombo.getSelectionModel().isEmpty() || unidadeCombo.getSelectionModel().isEmpty()){
            Error.message("Erro ao Cadastrar. Verifique os dados Inseridos!");
            return false;
        }

        //validação do produto
        if (idField.getText().equals("Novo")
                && (prodField.getText().trim().isEmpty() || prodField.getText().trim().length() <= 3)) {
            Error.message("Erro ao Cadastrar. Verifique os dados Inseridos!");
            return false;
        } else if (!idField.getText().equals("Novo")
                && (prodField.getText().trim().isEmpty() || prodField.getText().length() <= 3)) {
            Error.message("Erro ao Editar. Verifique os dados Inseridos!");
            return false;
        }

        if (idField.getText().equals("Novo")
                && (valorField.getText().trim().isEmpty() || Double.parseDouble(valorField.getText()) < 0)) {
            Error.message("Erro ao Cadastrar. Verifique os dados Inseridos!");
            return false;
        } else if (!idField.getText().equals("Novo")
                && (valorField.getText().trim().isEmpty() || Double.parseDouble(valorField.getText()) < 0)) {
            Error.message("Erro ao Editar. Verifique os dados Inseridos!");
            return false;
        }

        return true;
    }

    @Override
    public void save() {
        try {
            Produto p = new Produto();

            p.setMarca(marcaCombo.getValue());
            p.setCategoria(categoriaCombo.getValue());
            p.setDepartamento(departamentoCombo.getValue());
            p.setUnidade(unidadeCombo.getValue());
            p.setNome(prodField.getText());
            p.setDescricao(descricaoTar.getText());
            p.setQuantidade(0);
            p.setCustounitario(BigDecimal.valueOf(Double.parseDouble(valorField.getText())));
            p.setAtivo('1');

            prDao.add(p);
        }

        catch (Exception e) {
            Error.message(e.getMessage());
        }
    }

    @Override
    public void edit() {
        try {
            Produto p = (Produto) getSelected();

            p.setMarca(marcaCombo.getValue());
            p.setCategoria(categoriaCombo.getValue());
            p.setDepartamento(departamentoCombo.getValue());
            p.setUnidade(unidadeCombo.getValue());
            p.setNome(prodField.getText());
            p.setDescricao(descricaoTar.getText());
            p.setCustounitario(BigDecimal.valueOf(Double.parseDouble(valorField.getText())));


            prDao.update(p);
        } catch (Exception e) {
            Error.message(e.getMessage());
        }

        refresh();

    }

    @Override
    public void changeStatus() {

        try {
            Produto p = (Produto) getSelected();
            if (p.getAtivo() == '1')
                p.setAtivo('0');
            else {
                p.setAtivo('1');
            }
            prDao.update(p);

            refresh();
        }

        catch (Exception e) {
            Error.message(e.getMessage());
        }

    }

    @Override
    public void select() {
        selected = (Produto) getSelected();

        if (selected != null) {

            idField.setText(selected.getId() + "");
            marcaCombo.setValue(selected.getMarca());
            categoriaCombo.setValue(selected.getCategoria());
            departamentoCombo.setValue(selected.getDepartamento());
            unidadeCombo.setValue(selected.getUnidade());
            prodField.setText(selected.getNome());
            descricaoTar.setText(selected.getDescricao());
            valorField.setText(selected.getCustounitario() + "");
        }

        else {
            clear();
        }
    }

    @Override
    public void clear() {
        setSelected(null);

        idField.setText("Novo");
        marcaCombo.valueProperty().setValue(null);
        marcaCombo.setPromptText("Selecione a Marca");
        categoriaCombo.valueProperty().setValue(null);
        categoriaCombo.setPromptText("Selecione a Categoria");
        departamentoCombo.valueProperty().setValue(null);
        departamentoCombo.setPromptText("Selecione a Departamento");
        unidadeCombo.valueProperty().setValue(null);
        unidadeCombo.setPromptText("Selecione a Unidade");
        prodField.setText("");
        descricaoTar.setText("");
        valorField.setText("");
    }

    private void initComponents() {
        // TextFields
        idField.setDisable(true);

        marcaCombo.getItems().addAll(marca);
        marcaCombo.setPromptText("Selecione a Marca");
        marcaCombo.setPrefWidth(comboSize);
        categoriaCombo.getItems().addAll(categoria);
        categoriaCombo.setPromptText("Selecione a Categoria");
        categoriaCombo.setPrefWidth(comboSize);
        departamentoCombo.getItems().addAll(departamento);
        departamentoCombo.setPromptText("Selecione o Departameno");
        departamentoCombo.setPrefWidth(comboSize);
        unidadeCombo.getItems().addAll(unidade);
        unidadeCombo.setPromptText("Selecione a Unidade");
        unidadeCombo.setPrefWidth(comboSize);


        // Colunas da tabela
        TableColumn<Produto, Integer> id = new TableColumn<>("ID");
        TableColumn<Produto, String> marca = new TableColumn<>("Marca");
        TableColumn<Produto, String> cat = new TableColumn<>("Categoria");
        TableColumn<Produto, String> dep = new TableColumn<>("Departamento");
        TableColumn<Produto, String> uni = new TableColumn<>("Unidade");
        TableColumn<Produto, String> prod = new TableColumn<>("Produto");
        TableColumn<Produto, String> desc = new TableColumn<>("Descrição");
        TableColumn<Produto, String> qtd = new TableColumn<>("Quantidade");
        TableColumn<Produto, BigDecimal> valor = new TableColumn<>("Valor");
        TableColumn<Produto, Character> ativo = new TableColumn<>("Ativo");


        id.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("id"));
        marca.setCellValueFactory(new PropertyValueFactory<Produto, String>("marca"));
        cat.setCellValueFactory(new PropertyValueFactory<Produto, String>("categoria"));
        dep.setCellValueFactory(new PropertyValueFactory<Produto, String>("departamento"));
        uni.setCellValueFactory(new PropertyValueFactory<Produto, String>("unidade"));
        prod.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        desc.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));
        qtd.setCellValueFactory(new PropertyValueFactory<Produto, String>("quantidade"));
        valor.setCellValueFactory(new PropertyValueFactory<Produto, BigDecimal>("custounitario"));
        CellFormat.priceCellFormatting(valor);
        ativo.setCellValueFactory(new PropertyValueFactory<Produto, Character>("ativo"));


        // Tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(id, marca, cat, dep, uni, prod, desc, qtd, valor, ativo);

        //MaskField
        valorField.setMask("N!.NN");

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
    private int comboSize = 470;

    //fields
    private TextField idField = new TextField("Novo");
    private TextField prodField = new TextField();
    private TextArea descricaoTar = new TextArea();
    private MaskTextField valorField = new MaskTextField();

    private ProdutoDAO prDao = new ProdutoDAO();


}


