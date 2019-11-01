package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.parts.Window;
import com.yard.stockmanager.persistence.dao.EstoqueHasProdutoDAO;
import com.yard.stockmanager.persistence.dao.InsercaoDAO;
import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.entity.Produto;
import com.yard.stockmanager.useful.Error;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class ItensStockTab extends ManagementTab<Object[]> {

    private Object selected[] = new Object[10];
    private String id;
    private List<TableColumn<Object[], ?>> colsProd;
    private List<TableColumn<Object[], ?>> colsInsercoes;

    Estoque estoque;
    Produto produto;

    Window window;


    public ItensStockTab() {
        super("Cadastro de Itens por Estoque");
        initComponents();
    }

    @Override
    public void refresh() {
        List<Object[]> list = InsercaoDAO.getinserts(getFilter());
        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();
    }

    public void loadList() {

        List<Object[]> list = EstoqueHasProdutoDAO.getinserts(id);
        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();
    }

    @Override
    public boolean validate() {
        return true;
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
        selected = (Object[]) getSelected();
        id = tableView.getSelectionModel().getSelectedItem()[0].toString();
    }

    @Override
    public void clear() {

    }

    @Override
    public void details() {
        if(!tableView.getSelectionModel().isEmpty()){
            tableView.getColumns().removeAll();
            tableView.getColumns().clear();
            tableView.getColumns().addAll(colsProd);
            loadList();
        }else{
            Error.message("Selecione um registro para exibir seus detalhes");
        }

    }

    public void setDadosEstoque(){
        estoque = (Estoque) window.getSelected();


    }

    public void setDadosProduct(){
        produto = (Produto) window.getSelected();
    }

    private void initComponents() {

        //tabela de duas acoes
        this.setDoubleAction(true);
        //Text
        txtTitleEstq.setFont(Font.font("System", FontWeight.BOLD, 20));

        //TextFields estoque
        tfdCodEstq.setEditable(false);
        tfdCodEstq.setText("--selecione o Estoque--");
        tfdNomeEstq.setEditable(false);
        tfdEnderecoEstq.setEditable(false);
        tfdRuaEstq.setEditable(false);
        tfdBairroEstq.setEditable(false);

        //componentes das infos do produto
        //Text
        txtTitleProd.setFont(Font.font("System", FontWeight.BOLD, 20));

        //TextFields produto
        tfdCodProd.setEditable(false);
        tfdCodProd.setText("--selecione o Produto--");
        tfdNomeProd.setEditable(false);
        tfdMarcaProd.setEditable(false);
        tfdDepartamentoProd.setEditable(false);
        tfdCategoriaProd.setEditable(false);
        tfdUnidadeProd.setEditable(false);
        tfdValorProd.setEditable(false);

        //Inclui as Labels e TextFields nas linhas e colunas no painel da esquerda
        //dados do estoque
        innerGrid.addRow(0, hbxTitleEstq);
        innerGrid.addRow(1, labCodEstq, tfdCodEstq, btnBuscarEstq);
        innerGrid.addRow(2, labNomeEstq, tfdNomeEstq);
        innerGrid.addRow(3, labEnderecoEstq, tfdEnderecoEstq);
        innerGrid.addRow(4, labRuaEstq, tfdRuaEstq);
        innerGrid.addRow(5, labBairroEstq, tfdBairroEstq);
        //dados do produto
        innerGrid.addRow(6, hbxTitleProd);
        innerGrid.addRow(7, labCodProd, tfdCodProd, btnBuscarProd);
        innerGrid.addRow(8, labNomeProd, tfdNomeProd);
        innerGrid.addRow(9, labMarcaProd, tfdMarcaProd);
        innerGrid.addRow(10, labDepartamentoProd, tfdDepartamentoProd);
        innerGrid.addRow(11, labCategoriaProd, tfdCategoriaProd);
        innerGrid.addRow(12, labUnidadeProd, tfdUnidadeProd);
        innerGrid.addRow(13, labValorProd, tfdValorProd);

        //Botões
        //busca de estoque
        btnBuscarEstq.setVisible(true);
        btnBuscarEstq.setPrefSize(70, 10);


        //evento do botão buscarEstoque
        btnBuscarEstq.setOnAction(event -> {
            ManagementTab tab = new StockManagerTab();

            window = new Window( getTabPane().getScene(), tab, "Busca de Estoques");
            window.showAndWait();
        });

        //busca de produtos
        btnBuscarProd.setVisible(true);
        btnBuscarProd.setPrefSize(70, 10);
        btnBuscarProd.setDisable(false);

        //evento do botão BuscarProdutos
        btnBuscarProd.setOnAction( event -> {
            ManagementTab tab = new ProductRegisterTab();

            Window window = new Window( getTabPane().getScene(), tab, "Busca de Produtos");
            window.showAndWait();


        });

        //tabela de inserções
        TableColumn<Object[], Integer> idIns = new TableColumn<>("N. Inserção");
        TableColumn<Object[], String> func = new TableColumn<>("Funcionário.");
        TableColumn<Object[], Timestamp> data = new TableColumn<>("Data da Inserção");
        TableColumn<Object[], String> estq = new TableColumn<>("Estoque");
        TableColumn<Object[], Double> prod = new TableColumn<>("Qtd. Produtos");

        //Colunas da tabela de inserções
        //idIns.setCellValueFactory(new PropertyValueFactory<Object, Integer>("id"));
//        func.setCellValueFactory(new PropertyValueFactory<Object[], String>("user."));
//        data.setCellValueFactory(new PropertyValueFactory<Object[], Timestamp>("data"));
//        estq.setCellValueFactory(new PropertyValueFactory<Object[], String>("estoque"));
//        prod.setCellValueFactory(new PropertyValueFactory<Object[], Double>("produtos"));

        idIns.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[0]));
        func.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[1]));
        data.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[2]));
        estq.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[3]));
        prod.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[4]));

        colsInsercoes = Arrays.asList(idIns, func, data, estq, prod);


        //tabela de produtos
        TableColumn<Object[], Integer> id = new TableColumn<>("Cod. Prod.");
        TableColumn<Object[], String> nome = new TableColumn<>("Prod.");
        TableColumn<Object[], String> marca = new TableColumn<>("Marca");
        TableColumn<Object[], String> departamento = new TableColumn<>("Unidade");
        TableColumn<Object[], String> categoria = new TableColumn<>("Quantidade");
        TableColumn<Object[], Double> unidade = new TableColumn<>("Valor Unitário");

        //Colunas da tabela de produtos
//        id.setCellValueFactory(new PropertyValueFactory<Object, Integer>("Cod. Prod."));
//        nome.setCellValueFactory(new PropertyValueFactory<Object, String>("Prod."));
//        marca.setCellValueFactory(new PropertyValueFactory<Object, String>("Marca"));
//        departamento.setCellValueFactory(new PropertyValueFactory<Object, String>("Unidade"));
//        categoria.setCellValueFactory(new PropertyValueFactory<Object, String>("Quantidade"));
//        unidade.setCellValueFactory(new PropertyValueFactory<Object, Double>("Valor Unitário"));

        id.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[0]));
        nome.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[1]));
        marca.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[2]));
        departamento.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[3]));
        categoria.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[4]));
        unidade.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[5]));


        colsProd = Arrays.asList(id, nome, marca, departamento, categoria, unidade);

        //Tabela
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(colsInsercoes);
        refresh();

    }


    //Criação dos componentes da tela
    //componentes do estoque
    private Text txtTitleEstq = new Text("Dados do Estoque");
    private HBox hbxTitleEstq = new HBox(txtTitleEstq);

    private Label labCodEstq = new Label("Código:");
    private Label labNomeEstq = new Label("Nome:");
    private Label labEnderecoEstq = new Label("Endereço:");
    private Label labRuaEstq = new Label("Rua:");
    private Label labBairroEstq = new Label("Bairro:");

    private TextField tfdCodEstq = new TextField();
    private TextField tfdNomeEstq = new TextField();
    private TextField tfdEnderecoEstq = new TextField();
    private TextField tfdRuaEstq = new TextField();
    private TextField tfdBairroEstq = new TextField();

    //Componentes Produto
    private Text txtTitleProd = new Text("Dados do Produto");
    private HBox hbxTitleProd = new HBox(txtTitleProd);

    private Label labCodProd = new Label("Código:");
    private Label labNomeProd = new Label("Produto:");
    private Label labMarcaProd = new Label("Marca:");
    private Label labDepartamentoProd = new Label("Departamento:");
    private Label labCategoriaProd = new Label("Categoria:");
    private Label labUnidadeProd = new Label("Unidade:");
    private Label labValorProd = new Label("Valor:");

    private TextField tfdCodProd = new TextField();
    private TextField tfdNomeProd = new TextField();
    private TextField tfdMarcaProd = new TextField();
    private TextField tfdDepartamentoProd = new TextField();
    private TextField tfdCategoriaProd = new TextField();
    private TextField tfdUnidadeProd = new TextField();
    private TextField tfdValorProd = new TextField();

    private Button btnBuscarEstq = new Button("Buscar");
    private Button btnBuscarProd = new Button("Buscar");

}

