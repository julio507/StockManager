package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.parts.Window;
import com.yard.stockmanager.persistence.dao.*;
import com.yard.stockmanager.persistence.entity.*;
import com.yard.stockmanager.useful.CellFormat;
import com.yard.stockmanager.useful.ConfirmationDialog;
import com.yard.stockmanager.useful.Current;
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
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.*;

public class ItensStockTab extends ManagementTab<Object[]> {

    private Object selected[] = new Object[10]; // variavel para alocar objeto trazido na seleção do registro da tabela superior
    private Object bottomSelected[] = new Object[10]; // variavel para alocar objeto trazido na seleção do registro da tabela inferior
    private int insertIdSelection = 0; //variavel de marcação do id do registro selecionado
    private int insertId; //variavel de marcação do id de insercao para um registro em edição. Usado para possivel retorno dos registros
    private int productId; //variavel de marcação do id do produto
    private int estoqueId; //variavel de marcação do id do endereço para um registro em edição. Usado para possivel retorno dos registros
    private List<Object[]> tableTemp; //tabela de backup do registro de produtos para um registro em edição
    private List<TableColumn<Object[], ?>> colsProd; //colunas da tabela inferior
    private List<TableColumn<Object[], ?>> colsInsercoes; //colunas da tabela superior

    //variaveis DAO
    private InsercaoDAO insDAO = new InsercaoDAO();
    private FuncionarioDAO funcDAO = new FuncionarioDAO();
    private EstoqueHasProdutoDAO estqDAO = new EstoqueHasProdutoDAO();

    //variaveis para armazenamento de entidades
    private Estoque estoque = new Estoque();
    private Produto produto = new Produto();
    private Insercao insert = new Insercao();
    private Funcionario func = new Funcionario(); // temporario (até criação de metodo para verificar usuário atual)
    private EstoqueHasProduto estqProd = new EstoqueHasProduto();

    //Entidade para as janelas de seleção
    private Window window;

    //controladores
    private boolean changed = false; //sinaliza modificação em novas inserções
    private boolean isEdition = false; //sinaliza se é uma ação de ediçao de registro
    private boolean isEditionBottom = false; //sinaliza se é uma ação de ediçao de registro na tabela inferior
    private int lastInsertedID = 0; //grava o id da ultima inserçao realizada


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
    private void loadList() {
        List<Object[]> list = EstoqueHasProdutoDAO.getinserts(insertIdSelection + "");
        tableViewBottom.setItems(FXCollections.observableArrayList(list));
        tableViewBottom.refresh();
    }

    private void loadList(int idInsert) {
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
    private boolean validateInsert() {
        if (tfdCodProd.getText().equals("--selecione o Produto--")) {
            Error.message("Selecione o produto a ser inserido!");
            return false;
        } else if ((!tfdValorInsert.getText().trim().isEmpty() && (Double.parseDouble(tfdValorInsert.getText()) >= 0))
                && (!tfdQtdInsert.getText().trim().isEmpty() && (Double.parseDouble(tfdQtdInsert.getText()) > 0))) {
            return true;
        } else {
            Error.message("Verifique valor e quantidade do produto!");
            return false;
        }
    }

    @Override
    //metodo para salvamento de registros
    public void save() {

        if (!changed) {
            resetScreen();
        } else {
            char temp = ConfirmationDialog.confirm("Há modificações não salvas!", "Deseja salvar as modificações nesse registro?");
            if (temp == 'y') {
                resetScreen();
            } else if (temp == 'n') {
                if (isEdition) {
                    estqDAO.rollback(insertId, estoqueId, tableTemp);
                    resetScreen();
                } else {
                    estqDAO.rollback(lastInsertedID, estoqueId, tableTemp);
                    resetScreen();
                }
            }
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
        insertId = (int) selected[0];
        tableTemp = new ArrayList<>(tableViewBottom.getItems());

        tfdCodEstq.setText(estoque.getId() + "");
        tfdNomeEstq.setText(estoque.getNome());
        tfdEnderecoEstq.setText(estoque.getEndereco().getRua());
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
        if (isEdition) {
            EstoqueHasProduto ep = estqDAO.getById(new EstoqueHasProdutoId(insertId, estoque.getId(), Integer.parseInt(bottomSelected[0].toString())));
            ep.setAtivo('0');
            estqDAO.update(ep);
            changed = true;
        }
    }

    @Override
    //metodo para seleção de itens na tabela superior
    public void select() {
        if ((selected = (Object[]) getSelected()) != null) {
            insertIdSelection = Integer.parseInt(tableView.getSelectionModel().getSelectedItem()[0].toString());
            details();
        }
    }

    @Override
    //limpa os dados dos campos da tela
    public void clear() {
        save();
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
            tfdEnderecoEstq.setText(estoque.getEndereco().getRua());
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

            tfdValorInsert.setText(produto.getCustounitario() + "");
        }

    }

    @Override
    //Metodo de seleção dos registros da tabela inferior
    public void selectBottom() {
        if ((bottomSelected = (Object[]) getBottomSelected()) != null) {
            productId = (int) tableViewBottom.getSelectionModel().getSelectedItem()[0];
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
        char r = ConfirmationDialog.confirm("Remover Registro?", "Deseja realmente remover este registro?");
        if (r == 'y') {
            insDAO.delete(insDAO.getById(Integer.parseInt(selected[0].toString())));
            refresh();
        }

    }

    @Override
    //Edição dos registros da tebela inferior
    public void editBottomRegister() {
        btnBuscarProd.setDisable(true);
        isEditionBottom = true;

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

        char r = ConfirmationDialog.confirm("Remover registro?", "Desaje realmente remover este registro?");

        if (!isEdition) {
            EstoqueHasProduto ep = estqDAO.getById(new EstoqueHasProdutoId(lastInsertedID, estoque.getId(), Integer.parseInt(bottomSelected[0].toString())));
            if (r == 'y') {
                estqDAO.delete(ep);
                changed = true;
                loadList(lastInsertedID);
            }
        } else {
            EstoqueHasProduto ep = estqDAO.getById(new EstoqueHasProdutoId(insertId, estoque.getId(), Integer.parseInt(bottomSelected[0].toString())));
            if (r == 'y') {
                if (!compareTables()) {
                    estqDAO.delete(ep);
                    changed = true;
                } else {
                    changeStatus();
                    estqDAO.update(ep);
                    changed = true;
                }
                loadList(insertId);
            }
        }
    }

    //Trava a utilização dos botões da seção de produto
    public void lockPoduct() {
        btnBuscarProd.setDisable(true);
        tfdQtdInsert.setEditable(false);
        tfdValorInsert.setEditable(false);
        btnAdicionar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    //destrava a utilização dos botões da seção de produto
    public void unlockPoduct() {
        btnBuscarProd.setDisable(false);
        tfdQtdInsert.setEditable(true);
        tfdValorInsert.setEditable(true);
        btnAdicionar.setDisable(false);
        btnCancelar.setDisable(false);
    }

    //metodo para ação do botão adicionar
    public void adicionar() {

        if (validateInsert()) {
            //criação do obj
            estqProd.setEstoque(EstoqueDAO.getById(estoque.getId()));
            estqProd.setProduto(ProdutoDAO.getById(produto.getId()));
            estqProd.setQuantidade(Double.parseDouble(tfdQtdInsert.getText()));
            estqProd.setValorunitario(Double.parseDouble(tfdValorInsert.getText()));
            estqProd.setAtivo('1');

            if (!isEdition) {
                if (!changed && !isEditionBottom) {
                    //disabilita a tabela
                    tableView.setDisable(true);
                    //muda o estado o estado do registro para alterado
                    changed = true;
                    //desabilita os campos superiores e habilita os inferiores
                    disableUpperButtons();
                    enableBottomButtons();

                    //busca o usuário atual
                    func = funcDAO.getById(Current.getUser());

                    //dados da inserção
                    insert.setFuncionario(func);
                    insert.setData(new Date());
                    insert.setAtivo('1');

                    //id de controle para inserção
                    lastInsertedID = insDAO.addReturnid(insert);

                    //criação das chaves do obj
                    estqProd.setId(new EstoqueHasProdutoId(lastInsertedID, estoque.getId(), produto.getId()));
                    estqProd.setInsercao(InsercaoDAO.getById(lastInsertedID));

                    estqDAO.add(estqProd);
                    limpaCamposProduto();
                    loadList(lastInsertedID);
                    refresh();

                } else if (changed && !isEditionBottom) {
                    estqProd.setId(new EstoqueHasProdutoId(lastInsertedID, estoque.getId(), produto.getId()));
                    estqProd.setInsercao(InsercaoDAO.getById(lastInsertedID));

                    //popular a tabela pala validação de itens inseridos
                    loadList(lastInsertedID);

                    if (!isProductIdOnTable(estqProd.getProduto().getId())) {
                        estqDAO.add(estqProd);
                        changed = true;
                    } else {
                        if (!isSameValue(estqProd.getProduto().getId(), Double.parseDouble(tfdValorInsert.getText()))) {
                            char r = ConfirmationDialog.confirm("Há divergência de valores!",
                                    "O mesmo produto já esta inserido com valor diferente.\n " +
                                            "Deseja atualizar o valor do produto " + tfdNomeProd.getText() + " " +
                                            "para R$ " + tfdValorInsert.getText() + "?");
                            if (r == 'y') {
                                estqProd.setQuantidade(estqProd.getQuantidade() + Double.parseDouble(tableViewBottom.getItems().get(getRegPosition(estqProd.getProduto().getId()))[4].toString()));
                                estqProd.setValorunitario(Double.parseDouble(tfdValorInsert.getText()));
                                estqDAO.update(estqProd);
                                changed = true;
                            }
                        } else {
                            estqProd.setQuantidade(estqProd.getQuantidade() + Double.parseDouble(tableViewBottom.getItems().get(getRegPosition(estqProd.getProduto().getId()))[4].toString()));
                            estqDAO.update(estqProd);
                            changed = true;
                        }
                    }
                    limpaCamposProduto();
                    loadList(lastInsertedID);
                    refresh();
                } else {
                    estqProd.setId(new EstoqueHasProdutoId(lastInsertedID, estoque.getId(), produto.getId()));
                    estqProd.setInsercao(InsercaoDAO.getById(lastInsertedID));

                    estqProd.setQuantidade(estqProd.getQuantidade());
                    estqProd.setValorunitario(Double.parseDouble(tfdValorInsert.getText()));
                    estqDAO.updateProdOfInsert(lastInsertedID, estoque.getId(), produto.getId(), estqProd);
                    changed = true;

                    btnBuscarProd.setDisable(false);
                    isEditionBottom = false;
                    limpaCamposProduto();
                    loadList(lastInsertedID);
                    refresh();
                }
                //Edicao da tabela superior
            } else {
                if (!isEditionBottom) {

                    if (!changed) {
                        changed = true;
                    }

                    estqProd.setId(new EstoqueHasProdutoId(insertId, estoque.getId(), produto.getId()));
                    estqProd.setInsercao(InsercaoDAO.getById(insertId));

                    //popular a tabela pala validação de itens inseridos
                    loadList(insertId);

                    if (!isProductIdOnTable(estqProd.getProduto().getId())) {
                        estqDAO.add(estqProd);
                        changed = true;
                    } else {
                        if (!isSameValue(estqProd.getProduto().getId(), Double.parseDouble(tfdValorInsert.getText()))) {
                            char r = ConfirmationDialog.confirm("Há divergência de valores!",
                                    "O mesmo produto já esta inserido com valor diferente.\n " +
                                            "Deseja atualizar o valor do produto " + tfdNomeProd.getText() + " " +
                                            "para R$ " + tfdValorInsert.getText() + "?");
                            if (r == 'y') {
                                estqProd.setQuantidade(estqProd.getQuantidade() + Double.parseDouble(tableViewBottom.getItems().get(getRegPosition(estqProd.getProduto().getId()))[4].toString()));
                                estqProd.setValorunitario(Double.parseDouble(tfdValorInsert.getText()));
                                estqDAO.update(estqProd);
                                changed = true;
                            }
                        } else {
                            estqProd.setQuantidade(estqProd.getQuantidade() + Double.parseDouble(tableViewBottom.getItems().get(getRegPosition(estqProd.getProduto().getId()))[4].toString()));
                            estqDAO.update(estqProd);
                            changed = true;
                        }
                    }

                    limpaCamposProduto();
                    loadList(insertId);
                    refresh();
                } else {

                    changed = true;

                    estqProd.setId(new EstoqueHasProdutoId(insertId, estoque.getId(), produto.getId()));
                    estqProd.setInsercao(InsercaoDAO.getById(insertId));

                    estqProd.setQuantidade(estqProd.getQuantidade());
                    estqProd.setValorunitario(Double.parseDouble(tfdValorInsert.getText()));
                    estqDAO.updateProdOfInsert(insertId, estoque.getId(), produto.getId(), estqProd);

                    btnBuscarProd.setDisable(false);
                    isEditionBottom = false;
                    limpaCamposProduto();
                    loadList(insertId);
                    refresh();
                }
            }
        }
    }

    public void cancelar() {
        isEditionBottom = false;

        limpaCamposProduto();
        btnBuscarProd.setDisable(false);
    }

    //verifica se um produto já está na tabela, buscando pelo seu Código
    private boolean isProductIdOnTable(int p) {

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


    private Boolean isSameValue(int idProduto, double value) {
        boolean t = false;

        if (tableViewBottom.getItems().size() > 0) {
            for (int i = 0; i < tableViewBottom.getItems().size(); i++) {
                if (tableViewBottom.getItems().get(i)[0].equals(idProduto) &&
                        Double.parseDouble(tableViewBottom.getItems().get(i)[5].toString()) == value) {
                    t = true;
                    break;
                }
            }
        }

        return t;
    }

    //retorna o index de um registro de acordo com o seu codigo
    private int getRegPosition(int idProduto) {
        int p = 0;
        for (int i = 0; i < tableViewBottom.getItems().size(); i++) {
            if (tableViewBottom.getItems().get(i)[0].equals(idProduto)) {
                p = i;
                break;
            }
        }
        return p;
    }

    //limpa os campos que contem os dados do estoque
    private void limpaCamposEstoque() {
        tfdCodEstq.setText("--selecione o Estoque--");
        tfdNomeEstq.setText("");
        tfdEnderecoEstq.setText("");
        tfdRuaEstq.setText("");
        tfdBairroEstq.setText("");
    }

    //limpa os campos que contem os dados do produto
    private void limpaCamposProduto() {
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

    private void resetScreen() {
        //TextFields estoque
        limpaCamposEstoque();

        //trava o campo de produtos
        lockPoduct();
        //TextFields produto
        limpaCamposProduto();

        //desabilita os botões inferiores e habilita os superiores
        disableBottomButtons();
        enableUpperButtons();

        //reinicia as variaveis locais
        productId = 0;
        insertIdSelection = 0;
        insertId = 0;
        estoqueId = 0;
        changed = false;
        isEdition = false;
        isEditionBottom = false;
        lastInsertedID = 0;
        tableTemp = null;

        //reabilita a tela
        tableView.setDisable(false);

        //limpa a tabela inferior
        tableViewBottom.getColumns().removeAll();
        tableViewBottom.getColumns().clear();
        refresh();
    }


    //compara o conteudo da tabela com o conteudo anterior
    // (usar em caso de edição de dados para permitir cancelamento da operação sem perda de dados)
    private boolean compareTables() {
        boolean r = true;

        for (Object t : tableViewBottom.getItems()) {
            if (!tableTemp.contains(t)) {
                r = false;
                break;
            }
        }

        for (Object t : tableTemp) {
            if (!tableViewBottom.getItems().contains(t)) {
                r = false;
                break;
            }
        }
        return r;
    }

    private byte[] fileChooser(){
        byte[] file = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\1511 FOX"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf")
        );
        File selectedFile = fileChooser.showOpenDialog(getTabPane().getScene().getWindow());

        try {
            file = Base64.getEncoder().encode(Files.readAllBytes(selectedFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
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
        insertControlGrid.addRow(3, btnCancelar);
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

        btnAdicionar.setPrefSize(100, 10);
        //evento do botão Adicionar
        btnAdicionar.setOnAction(event -> {
            adicionar();
        });

        btnCancelar.setPrefSize(100, 10);
        //evento do botão cancelar
        btnCancelar.setOnAction(event -> {
            cancelar();
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
    private Button btnCancelar = new Button("Cancelar");

    private Button btnBuscarEstq = new Button("Buscar");
    private Button btnBuscarProd = new Button("Buscar");
}

