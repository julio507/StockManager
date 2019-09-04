package com.yard.stockmanager.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import parts.ManagementTab;
import java.util.Arrays;
import java.util.List;

import java.awt.*;

public class StockManagerTab extends ManagementTab<Object>
{

    private Stage stage;
    private Font font = new Font(14);

    public StockManagerTab()
    {
        super("Gerenciamento de Estoque");
        initComponents();
    }

    public void setStage(Stage s)
    {
        this.stage = s;
    }
    public Stage getStage()
    {
        return stage;
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


    private void initComponents()
    {

        innerGrid.addRow(0,labNome, tfdNome);
        innerGrid.addRow(1,labRua, tfdRua);
        innerGrid.addRow(2,labBairro, tfdBairro);
        innerGrid.addRow(3,labNumero, tfdNumero);
        innerGrid.addRow(4,labCep, tfdCep);
        innerGrid.addRow(5,labComplemento, tfdComplemento);
        innerGrid.addRow(6,labDescricao, tfdDescricao);
        innerGrid.addRow(7,labTelefone, tfdTelefone);
        innerGrid.addRow(8,btnSalva);


        TableColumn<Object, Integer> id = new TableColumn<>("ID");
        TableColumn<Object, String> nome = new TableColumn<>("Nome");
        TableColumn<Object, String> rua = new TableColumn<>("Rua");
        TableColumn<Object, String> bairro = new TableColumn<>("Bairro");
        TableColumn<Object, Integer> numero = new TableColumn<>("Número");
        TableColumn<Object, Integer> cep = new TableColumn<>("CEP");
        TableColumn<Object, String> complemento = new TableColumn<>("Complemento");
        TableColumn<Object, String> descricao = new TableColumn<>("Descrição");
        TableColumn<Object, Integer> telefone = new TableColumn<>("Telefone");

        id.setCellValueFactory(new PropertyValueFactory<Object, Integer>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<Object, String>("nome"));
        rua.setCellValueFactory(new PropertyValueFactory<Object, String>("rua"));
        bairro.setCellValueFactory(new PropertyValueFactory<Object, String>("bairro"));
        numero.setCellValueFactory(new PropertyValueFactory<Object, Integer>("numero"));
        cep.setCellValueFactory(new PropertyValueFactory<Object, Integer>("cep"));
        complemento.setCellValueFactory(new PropertyValueFactory<Object, String>("complemento"));
        descricao.setCellValueFactory(new PropertyValueFactory<Object, String>("descricao"));
        telefone.setCellValueFactory(new PropertyValueFactory<Object, Integer>("telefone"));

        tableView.getColumns().addAll(
            id,
            nome,
            rua,
            bairro,
            numero,
            cep,
            complemento,
            descricao,
            telefone
        );

        refresh();

    }

    //Criação dos componentes da tela
    private Label labRua = new Label("Rua:");
    private Label labBairro = new Label("Bairro:");
    private Label labNumero = new Label("Número:");
    private Label labCep = new Label("CEP:");
    private Label labComplemento = new Label("Complemento:");
    private Label labNome = new Label("Nome:");
    private Label labDescricao = new Label("Descrição:");
    private Label labTelefone = new Label("Telefone:");

    private TextField tfdRua = new TextField();
    private TextField tfdBairro = new TextField();
    private TextField tfdNumero = new TextField();
    private TextField tfdCep = new TextField();
    private TextField tfdComplemento = new TextField();
    private TextField tfdNome = new TextField();
    private TextField tfdDescricao = new TextField();
    private TextField tfdTelefone = new TextField();

    private Button btnSalva = new Button("Salvar");
    private Button btnEditar = new Button("Editar");

    private TableView tabela = new TableView<>();
    private TableColumn colunaCodigo = new TableColumn<>("Código");
    private TableColumn colunaNome = new TableColumn<>("Nome");
    private TableColumn colunaRua = new TableColumn<>("Rua");
    private TableColumn colunaNumero = new TableColumn<>("Número");
    private TableColumn colunaBairro = new TableColumn<>("Bairro");
    private TableColumn colunaComplemento = new TableColumn<>("Complemento");
    private TableColumn colunaCep = new TableColumn<>("CEP");
    private TableColumn colunaTelefone = new TableColumn<>("Telefone");
    private TableColumn colunaDescricao = new TableColumn<>("Descrição");

    private GridPane telaPrincipal = new GridPane();
    private GridPane telaEsquerda = new GridPane();
    private GridPane telaDireita = new GridPane();
}
