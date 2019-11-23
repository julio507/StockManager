package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.parts.Window;
import com.yard.stockmanager.persistence.dao.*;
import com.yard.stockmanager.persistence.entity.*;
import com.yard.stockmanager.useful.CellFormat;
import com.yard.stockmanager.useful.ConfirmationDialog;
import com.yard.stockmanager.useful.Error;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItensStockTab extends ManagementTab<Object[]> {

    private Object selected[] = new Object[10];
    private Object bottomSelected[] = new Object[10];
    private int insertIdSelection = 0;
    private int insertId;
    private int productId;
    private int estoqueId;
    private List<TableColumn<Object[], ?>> colsProd;
    private List<TableColumn<Object[], ?>> colsInsercoes;
    private List<Object[]> tableTemp;

    private InsercaoDAO insDAO = new InsercaoDAO();
    private FuncionarioDAO funcDAO = new FuncionarioDAO();
    private EstoqueHasProdutoDAO estqDAO = new EstoqueHasProdutoDAO();

    private Estoque estoque = new Estoque();
    private Produto produto = new Produto();
    private Insercao insert = new Insercao();
    private Funcionario func = new Funcionario(); // temporario (até criação de metodo para verificar usuário atual)
    private EstoqueHasProduto estqProd = new EstoqueHasProduto();

    //Entidade para as janelas de seleção
    private Window window;

    //controladores
    private boolean changed = false;
    private boolean isEdition = false;
    private int lastInsertedID = 0;


    public ItensStockTab() {
        super("Cadastro de Itens por Estoque", true);
        initComponents();
    }

    @Override
    //carrega os dados na tabela superior
    public void refresh() {
        List<Object[]> list = InsercaoDAO.getinserts(getFilter());
        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();
    }

    //caregga os dados na tabela inferior
    public void loadList() {
        List<Object[]> list = EstoqueHasProdutoDAO.getinserts(insertIdSelection + "");
        tableViewBottom.setItems(FXCollections.observableArrayList(list));
        tableViewBottom.refresh();
    }

    public void loadList(int idInsert) {
        tableViewBottom.getColumns().removeAll();
        tableViewBottom.getColumns().clear();
        tableViewBottom.getColumns().addAll(colsProd);
        List<Object[]> list = EstoqueHasProdutoDAO.getinserts(idInsert + "");
        tableViewBottom.setItems(FXCollections.observableArrayList(list));
        tableViewBottom.refresh();
    }

    @Override
    //validação do salvamento final
    public boolean validate() {

        return true;
    }


    //metodo para validação de inserção de produto
    public boolean validateInsert() {
        if(tfdCodProd.getText().equals("--selecione o Produto--")) {
            Error.message("Selecione o produto a ser inserido!");
            return false;
        }
        else if ((!tfdValorInsert.getText().trim().isEmpty() && (Double.parseDouble(tfdValorInsert.getText()) >= 0))
                && (!tfdQtdInsert.getText().trim().isEmpty() && (Double.parseDouble(tfdQtdInsert.getText()) > 0))){
            return true;
        }
        else {
            Error.message("Verifique valor e quantidade do produto!");
            return false;
        }
    }

    @Override
    //metodo para salvamento de registros
    public void save() {
        //atualizar/alterar todos os registro de EstoqueHasProduto para o estoque selecionado

        if(tfdCodEstq.getText().equals(estoqueId) || compareTables()){
            //salva
        }
    }

    @Override
    //metodo para a edição dos registros da tabela superior
    public void edit() {
        estoque = (Estoque) selected[3];

        //variavel de controle
        isEdition = true;

        //dados auxiliares para comparação
        estoqueId = estoque.getId();
        insertId = (int)selected[0];
        tableTemp  = new ArrayList<>(tableViewBottom.getItems());

//        //debug
//        tableTemp.remove(0);
//        System.out.println("temp");
//        for (Object e : tableTemp) {
//            System.out.println(e);
//        }
//
//        System.out.println("table");
//        for (Object e : tableViewBottom.getItems()) {
//            System.out.println(e);
//        }
//        System.out.println(compareTables());

        tfdCodEstq.setText(estoque.getId() + "");
        tfdNomeEstq.setText(estoque.getNome());
        tfdEnderecoEstq.setText(estoque.getEndereco().getEndereco());
        tfdRuaEstq.setText(estoque.getEndereco().getRua());
        tfdBairroEstq.setText(estoque.getEndereco().getBairro());

        //disabilita a tabela
        tableView.setDisable(true);

        unlockPoduct();
        disableUpperButtons();
        enableBottomButtons();
    }

    @Override
    public void changeStatus() {

    }

    @Override
    //metodo para seleção de itens na tabela superior
    public void select() {
        if((selected = (Object[]) getSelected()) != null) {
            insertIdSelection = Integer.parseInt(tableView.getSelectionModel().getSelectedItem()[0].toString());
            details();
        }
    }

    @Override
    //limpa os dados dos campos da tela
    public void clear() {

        if (!changed) {
            //TextFields estoque
            limpaCamposEstoque();

            lockPoduct();
            //TextFields produto
            limpaCamposProduto();

            disableBottomButtons();
            enableUpperButtons();

            productId = 0;
            insertIdSelection = 0;
            insertId = 0;
            estoqueId = 0;
            changed = false;
            isEdition = false;
            lastInsertedID = 0;
            tableTemp = null;

            tableView.setDisable(false);

            tableViewBottom.getColumns().removeAll();
            tableViewBottom.getColumns().clear();
        }else{

            char temp = ConfirmationDialog.confirm("Há modificações não salvas!", "Deseja salvar as modificações nesse registro?");
            if(temp == 's'){
                //metodo para tratar resposta
            }else if(temp == 'n'){

            }

        }

    }

    @Override
    //metodo que seleciona um registro da tabela superior e com base no registro selecionado popula a tabela inferior
    public void details() {
        if (!tableView.getSelectionModel().isEmpty()) {
            tableViewBottom.getColumns().removeAll();
            tableViewBottom.getColumns().clear();
            tableViewBottom.getColumns().addAll(colsProd);
            loadList();
        }
    }


    //metodo para setar os valores trazidos na tela de busca de Estoque
    public void setDadosEstoque() {
        if ((estoque = (Estoque) window.getSelected()) != null) {

            tfdCodEstq.setText(estoque.getId() + "");
            tfdNomeEstq.setText(estoque.getNome());
            tfdEnderecoEstq.setText(estoque.getEndereco().getEndereco());
            tfdRuaEstq.setText(estoque.getEndereco().getRua());
            tfdBairroEstq.setText(estoque.getEndereco().getBairro());
        }

    }

    //metodo para setar os valores trazidos na tela de busca de produtos
    public void setDadosProduct() {

        if ((produto = (Produto) window.getSelected()) != null) {

            tfdCodProd.setText(produto.getId() + "");
            tfdNomeProd.setText(produto.getNome());
            tfdMarcaProd.setText(produto.getMarca().getNome());
            tfdDepartamentoProd.setText(produto.getDepartamento().getNome());
            tfdCategoriaProd.setText(produto.getCategoria().getNome());
            tfdUnidadeProd.setText(produto.getUnidade().getSigla());
            tfdValorProd.setText(produto.getCustounitario() + "");
        }

    }

    @Override
    //Metodo de seleção dos registros da tabela inferior
    public void selectBottom() {
        if((bottomSelected = (Object[]) getBottomSelected()) != null) {
            productId = (int)tableViewBottom.getSelectionModel().getSelectedItem()[0];
            produto = ProdutoDAO.getById(productId);
        }
    }

    @Override
    //Edição dos registros da tabela superior
    public void editUpperRegister() {
        //metodo de edição
        edit();
    }

    @Override
    //Remoção dos registros da tabela superior
    public void removeUpperRegister() {
    }

    @Override
    //Edição dos registros da tebela inferior
    public void editBottomRegister() {
        tfdCodProd.setText(produto.getId() + "");
        tfdNomeProd.setText(produto.getNome());
        tfdMarcaProd.setText(produto.getMarca().toString());
        tfdDepartamentoProd.setText(produto.getDepartamento().toString());
        tfdCategoriaProd.setText(produto.getCategoria().toString());
        tfdUnidadeProd.setText(produto.getUnidade().toString());
        tfdValorProd.setText(produto.getCustounitario() + "");

        tfdQtdInsert.setText(bottomSelected[4].toString());
        tfdValorInsert.setText(bottomSelected[5].toString().replaceAll("R$ ", ""));
    }

    @Override
    //Remoção dos registros da tebela inferior
    public void removeBottomRegister() {
    }

    //Trava a utilização dos botões da seção de produto
    public void lockPoduct() {
        btnBuscarProd.setDisable(true);
        tfdQtdInsert.setEditable(false);
        tfdValorInsert.setEditable(false);
        btnAdicionar.setDisable(true);
    }

    //destrava a utilização dos botões da seção de produto
    public void unlockPoduct() {
        btnBuscarProd.setDisable(false);
        tfdQtdInsert.setEditable(true);
        tfdValorInsert.setEditable(true);
        btnAdicionar.setDisable(false);
    }

    //metodo para ação do botão adicionar
    public void adicionar(){

        validateInsert();//teste

        if (!isEdition) {
            if (!changed && validate()) {

                //disabilita a tabela
                tableView.setDisable(true);

                changed = true;
                disableUpperButtons();
                enableBottomButtons();

                //temporário
                func = funcDAO.getByLogin("root");

                //dados da inserção
                insert.setFuncionario(func);
                insert.setData(Date.valueOf(LocalDate.now()));
                insert.setAtivo('1');

                //id de controle para inserção
                lastInsertedID = insDAO.addReturnid(insert);

                estqProd.setId(new EstoqueHasProdutoId(lastInsertedID, estoque.getId(), produto.getId()));
                estqProd.setEstoque(EstoqueDAO.getById(estoque.getId()));
                estqProd.setInsercao(InsercaoDAO.getById(lastInsertedID));
                estqProd.setProduto(ProdutoDAO.getById(produto.getId()));
                estqProd.setQuantidade(Double.parseDouble(tfdQtdInsert.getText()));
                estqProd.setValorunitario(Double.parseDouble(tfdValorInsert.getText()));
                estqProd.setAtivo('1');

                loadList(lastInsertedID);
                System.out.println(tableViewBottom.getItems().size());

                if (!isProductIdOnTable(estqProd.getProduto().getId())) {
                    estqDAO.add(estqProd);
                } else {
                    if (!isProductOnTable(estqProd)) {
                        //update???criar metodo increaseQTD decreaseQTD
                    } else {
                        char r = ConfirmationDialog.confirm("Há divergência de valores",
                                "o Mesmo Produto ja esta inserido com valor diferente.\n " +
                                        "Deseja Atualizar o valor do Produto " + tfdNomeProd.getText() +
                                        " para R$ " + tfdValorInsert + " ?");
                        if (r == 'y') {
                            //update valor
                        } else {
                            //calcelar
                        }
                    }
                }

                limpaCamposProduto();
                refresh();
                loadList(lastInsertedID);

            } else if (validate()) {

                estqProd.setId(new EstoqueHasProdutoId(lastInsertedID, estoque.getId(), produto.getId()));
                estqProd.setEstoque(EstoqueDAO.getById(estoque.getId()));
                estqProd.setInsercao(InsercaoDAO.getById(lastInsertedID));
                estqProd.setProduto(ProdutoDAO.getById(produto.getId()));
                estqProd.setQuantidade(Double.parseDouble(tfdQtdInsert.getText()));
                estqProd.setValorunitario(Double.parseDouble(tfdValorInsert.getText()));
                estqProd.setAtivo('1');

                if (!isProductIdOnTable(estqProd.getProduto().getId())) {
                    estqDAO.add(estqProd);
                } else {
                    if (!isProductOnTable(estqProd)) {
                        //update???criar metodo increaseQTD decreaseQTD
                    } else {
                        char r = ConfirmationDialog.confirm("Há divergência de valores",
                                "o Mesmo Produto ja esta inserido com valor diferente.\n " +
                                        "Deseja Atualizar o valor do Produto " + tfdNomeProd.getText() +
                                        " para R$ " + tfdValorInsert + " ?");
                        if (r == 'y') {
                            //update valor
                        } else {
                            //calcelar
                        }
                    }
                }

                limpaCamposProduto();
                loadList(lastInsertedID);
            }
        }else{

            estqProd.setId(new EstoqueHasProdutoId(insertId, estoque.getId(), produto.getId()));
            estqProd.setEstoque(EstoqueDAO.getById(estoque.getId()));
            estqProd.setInsercao(InsercaoDAO.getById(insertId));
            estqProd.setProduto(ProdutoDAO.getById(produto.getId()));
            estqProd.setQuantidade(Double.parseDouble(tfdQtdInsert.getText()));
            estqProd.setValorunitario(Double.parseDouble(tfdValorInsert.getText()));
            estqProd.setAtivo('1');

            if (!isProductIdOnTable(estqProd.getProduto().getId())) {
                estqDAO.add(estqProd);
            } else {
                if (!isProductOnTable(estqProd)) {
                    //update???criar metodo increaseQTD decreaseQTD
                } else {
                    char r = ConfirmationDialog.confirm("Há divergência de valores",
                            "o Mesmo Produto ja esta inserido com valor diferente.\n " +
                                    "Deseja Atualizar o valor do Produto " + tfdNomeProd.getText() +
                                    " para R$ " + tfdValorInsert + " ?");
                    if (r == 'y') {
                        //update valor
                    } else {
                        //calcelar
                    }
                }
            }

            limpaCamposProduto();
            loadList(insertId);

        }

    }

    //verifica se um produto já está na tabela, buscando pelo seu Código
    private boolean isProductIdOnTable(int p){

        boolean t = false;

        if (tableViewBottom.getItems().size() > 0) {
            for (int i = 0; i < tableViewBottom.getItems().size(); i++) {
                if (tableViewBottom.getItems().get(i)[0].equals(p)) {
                    t = true;
                    break;
                }
            }
        }

        return t;
    }

    private boolean isProductOnTable(EstoqueHasProduto eProduct){
        boolean t = false;

        if(tableViewBottom.getItems().size() > 0) {
            for (int i = 0; i < tableViewBottom.getItems().size(); i++) {
                if (tableViewBottom.getItems().get(i)[0].equals(eProduct.getProduto().getId()) &&
                        tableViewBottom.getItems().get(i)[4].equals(eProduct.getQuantidade()) &&
                        tableViewBottom.getItems().get(i)[5].equals(eProduct.getValorunitario())) {
                    t = true;
                    break;
                }
            }
        }

        return t;
    }

    //limpa os campos que contem os dados do estoque
    private void limpaCamposEstoque(){
        tfdCodEstq.setText("--selecione o Estoque--");
        tfdNomeEstq.setText("");
        tfdEnderecoEstq.setText("");
        tfdRuaEstq.setText("");
        tfdBairroEstq.setText("");
    }

    //limpa os campos que contem os dados do produto
    private void limpaCamposProduto(){
        tfdCodProd.setText("--selecione o Produto--");
        tfdNomeProd.setText("");
        tfdMarcaProd.setText("");
        tfdDepartamentoProd.setText("");
        tfdCategoriaProd.setText("");
        tfdUnidadeProd.setText("");
        tfdValorProd.setText("");

        tfdQtdInsert.setText("");
        tfdValorInsert.setText("");
    }

    private boolean compareTables(){
        boolean r = true;
        for (Object t : tableViewBottom.getItems()) {
            if (!tableTemp.contains(t)){
                System.out.println("pass");
                r = false;
                break;
            }
        }

        return r;
    }


    private void initComponents() {
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
        //controles de inserção

        insertControlGrid.addRow(0, labQtdInsert, tfdQtdInsert);
        insertControlGrid.addRow(1, labValorInsert, tfdValorInsert);
        insertControlGrid.addRow(2, btnAdicionar);
        insertControlGrid.setPadding(new Insets(50, 0, 0, 0));

        innerGrid.addRow(14, insertControlGrid);

        //Botões
        //busca de estoque
        btnBuscarEstq.setVisible(true);
        btnBuscarEstq.setPrefSize(70, 10);


        //evento do botão buscarEstoque
        btnBuscarEstq.setOnAction(event -> {
            ManagementTab tab = new StockManagerTab();

            window = new Window(getTabPane().getScene(), tab, "Busca de Estoques");
            window.showAndWait();

            if (window.getSelected() != null) {
                setDadosEstoque();
                unlockPoduct();
            }
        });

        //busca de produtos
        btnBuscarProd.setVisible(true);
        btnBuscarProd.setPrefSize(70, 10);
        lockPoduct();

        //evento do botão BuscarProdutos
        btnBuscarProd.setOnAction(event -> {
            ManagementTab tab = new ProductRegisterTab();

            window = new Window(getTabPane().getScene(), tab, "Busca de Produtos");
            window.showAndWait();

            if (window.getSelected() != null) {
                setDadosProduct();
            }
        });

        //evento do botão Adicionar
        btnAdicionar.setOnAction(event -> {
            adicionar();
        });

        //tabela de inserções
        TableColumn<Object[], Integer> idIns = new TableColumn<>("N. Inserção");
        TableColumn<Object[], String> func = new TableColumn<>("Funcionário.");
        TableColumn<Object[], Timestamp> data = new TableColumn<>("Data da Inserção");
        TableColumn<Object[], String> estq = new TableColumn<>("Estoque");
        TableColumn<Object[], Double> prod = new TableColumn<>("Qtd. Produtos");

        idIns.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[0]));
        func.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[1]));
        data.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[2]));
        CellFormat.dateCellFormatting(data, true);
        estq.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[3]));
        prod.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[4]));

        colsInsercoes = Arrays.asList(idIns, func, data, estq, prod);


        //tabela de produtos
        TableColumn<Object[], Integer> id = new TableColumn<>("Cod. Prod.");
        TableColumn<Object[], String> nome = new TableColumn<>("Prod.");
        TableColumn<Object[], String> marca = new TableColumn<>("Marca");
        TableColumn<Object[], String> unidade = new TableColumn<>("Unidade");
        TableColumn<Object[], String> qtd = new TableColumn<>("Quantidade");
        TableColumn<Object[], Double> valUnitario = new TableColumn<>("Valor Unitário");


        id.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[0]));
        nome.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[1]));
        marca.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[2]));
        unidade.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[3]));
        qtd.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[4]));
        valUnitario.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue()[5]));
        CellFormat.priceDoubleCellFormatting(valUnitario);

        colsProd = Arrays.asList(id, nome, marca, unidade, qtd, valUnitario);

        //Tabelas
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(1500);
        tableViewBottom.setPrefWidth(1500);
        tableView.getColumns().addAll(colsInsercoes);
        refresh();

        enableUpperButtons();
        disableBottomButtons();

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

    //componentes da inserção
    private GridPane insertControlGrid = new GridPane();

    private Label labQtdInsert = new Label("Quantidade:");
    private Label labValorInsert = new Label("Valor Unitário:");

    private TextField tfdQtdInsert = new TextField();
    private TextField tfdValorInsert = new TextField();

    private Button btnAdicionar = new Button("Adicionar");

    private Button btnBuscarEstq = new Button("Buscar");
    private Button btnBuscarProd = new Button("Buscar");

}

