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

import java.util.Arrays;
import java.util.List;

import java.awt.*;

public class PeopleRegisterTab extends Tab {

    private Stage stage;
    private Font font = new Font(14);

    public PeopleRegisterTab()
    {
        super("Cadastro de Pessoas");
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


    private void initComponents() {
        // Labels
        labCodigo.setFont(font);
        labCodigo.setPrefSize(labWidth,labHeight);

        labDenominacaoSocial.setFont(font);
        labDenominacaoSocial.setPrefSize(labWidth,labHeight);

        labNome.setFont(font);
        labNome.setPrefSize(labWidth,labHeight);

        labTelefone.setFont(font);
        labTelefone.setPrefSize(labWidth,labHeight);

        labEmail.setFont(font);
        labEmail.setPrefSize(labWidth,labHeight);

        labCnpj.setFont(font);
        labCnpj.setPrefSize(labWidth,labHeight);

        labCpf.setFont(font);
        labCpf.setPrefSize(labWidth,labHeight);

        labRua.setFont(font);
        labRua.setPrefSize(labWidth,labHeight);

        labNumero.setFont(font);
        labNumero.setPrefSize(labWidth,labHeight);

        labBairro.setFont(font);
        labBairro.setPrefSize(labWidth,labHeight);

        labCep.setFont(font);
        labCep.setPrefSize(labWidth,labHeight);

        labCidade.setFont(font);
        labCidade.setPrefSize(labWidth,labHeight);

        labUf.setFont(font);
        labUf.setPrefSize(labWidth,labHeight);

        labObservacoes.setFont(font);
        labObservacoes.setPrefSize(labWidth,labHeight);

        //TextFields
        tfdCodigo.setEditable(true);
        tfdCodigo.setFont(font);
        tfdCodigo.setPrefSize(tfdWidth,tfdHeight);

        tfdDenominacaoSocial.setEditable(true);
        tfdDenominacaoSocial.setFont(font);
        tfdDenominacaoSocial.setPrefSize(tfdWidth,tfdHeight);

        tfdNome.setEditable(true);
        tfdNome.setFont(font);
        tfdNome.setPrefSize(tfdWidth,tfdHeight);

        tfdTelefone.setEditable(true);
        tfdTelefone.setFont(font);
        tfdTelefone.setPrefSize(tfdWidth,tfdHeight);

        tfdEmail.setEditable(true);
        tfdEmail.setFont(font);
        tfdEmail.setPrefSize(tfdWidth,tfdHeight);

        tfdCnpj.setEditable(true);
        tfdCnpj.setFont(font);
        tfdCnpj.setPrefSize(tfdWidth,tfdHeight);

        tfdCpf.setEditable(true);
        tfdCpf.setFont(font);
        tfdCpf.setPrefSize(tfdWidth,tfdHeight);

        tfdRua.setEditable(true);
        tfdRua.setFont(font);
        tfdRua.setPrefSize(tfdWidth,tfdHeight);

        tfdNumero.setEditable(true);
        tfdNumero.setFont(font);
        tfdNumero.setPrefSize(tfdWidth,tfdHeight);

        tfdBairro.setEditable(true);
        tfdBairro.setFont(font);
        tfdBairro.setPrefSize(tfdWidth,tfdHeight);

        tfdCep.setEditable(true);
        tfdCep.setFont(font);
        tfdCep.setPrefSize(tfdWidth,tfdHeight);

        tfdCidade.setEditable(true);
        tfdCidade.setFont(font);
        tfdCidade.setPrefSize(tfdWidth,tfdHeight);

        tfdUf.setEditable(true);
        tfdUf.setFont(font);
        tfdUf.setPrefSize(tfdWidth,tfdHeight);

        tfdObservacoes.setEditable(true);
        tfdObservacoes.setFont(font);
        tfdObservacoes.setPrefSize(tfdWidth,tfdHeight);

        tfdTipo.setEditable(true);
        tfdTipo.setFont(font);
        tfdTipo.setPrefSize(tfdWidth,tfdHeight);





        //Botões
        btnSalva.setVisible(true);
        btnSalva.setPrefSize(100,30);
        btnEditar.setVisible(true);
        btnEditar.setPrefSize(100,30);

        //Colunas da tabela
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("Código"));
        colunaDenominacaoSocial.setCellValueFactory(new PropertyValueFactory<>("Denominação Social"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("E-mail"));
        colunaCnpj.setCellValueFactory(new PropertyValueFactory<>("CNPJ"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        colunaRua.setCellValueFactory(new PropertyValueFactory<>("Rua"));
        colunaNumero.setCellValueFactory(new PropertyValueFactory<>("Número"));
        colunaBairro.setCellValueFactory(new PropertyValueFactory<>("Bairro"));
        colunaCep.setCellValueFactory(new PropertyValueFactory<>("CEP"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("Cidade"));
        colunaUf.setCellValueFactory(new PropertyValueFactory<>("UF"));
        colunaObservacoes.setCellValueFactory(new PropertyValueFactory<>("Observações"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));



        //Tabela
        tabela.setPrefSize(1000, 1000);
        tabela.getColumns().addAll(colunaCodigo, colunaDenominacaoSocial, colunaNome, colunaTelefone, colunaEmail, colunaCnpj, colunaCpf, colunaRua, colunaNumero, colunaBairro, colunaCep, colunaCidade, colunaUf, colunaObservacoes, colunaTipo);

        //Inicializa os paineis da tela
        telaPrincipal.setPadding(new Insets(0));
        telaEsquerda.setPadding(new Insets(100));
        telaDireita.setPadding(new Insets(100));

        //Inclui as Labels e TextFields nas linhas e colunas no painel da esquerda
        telaEsquerda.setAlignment(Pos.TOP_RIGHT);
        telaEsquerda.addRow(0, labCodigo,tfdCodigo);
        telaEsquerda.addRow(1, labDenominacaoSocial, tfdDenominacaoSocial);
        telaEsquerda.addRow(2, labNome, tfdNome);
        telaEsquerda.addRow(3, labTelefone, tfdTelefone);
        telaEsquerda.addRow(4, labEmail, tfdEmail);
        telaEsquerda.addRow(5, labCnpj, tfdCnpj);
        telaEsquerda.addRow(6, labCpf, tfdCpf);
        telaEsquerda.addRow(7, labRua, tfdRua);
        telaEsquerda.addRow(8, labNumero, tfdNumero);
        telaEsquerda.addRow(9, labBairro, tfdBairro);
        telaEsquerda.addRow(10, labCep, tfdCep);
        telaEsquerda.addRow(11, labCidade, tfdCidade);
        telaEsquerda.addRow(12, labUf, tfdUf);
        telaEsquerda.addRow(13, labObservacoes, tfdObservacoes);
        telaEsquerda.addRow(14, labTipo, tfdTipo);
        telaEsquerda.addRow(8, btnSalva);

        telaDireita.setAlignment(Pos.TOP_RIGHT);
        telaDireita.addRow(0, tabela);
        telaDireita.addRow(1, btnEditar);

        telaPrincipal.addRow(0,telaEsquerda, telaDireita);

        setContent(telaPrincipal);

    }

    //Iniciação das variaveis
    private double labWidth = 240;
    private double labHeight = 50;
    private double tfdWidth = 300;
    private double tfdHeight = 20;

    //Criação dos componentes da tela
    private Label labCodigo = new Label("Código:");
    private Label labDenominacaoSocial = new Label("Denominação Social");
    private Label labNome = new Label("Nome:");
    private Label labTelefone = new Label("Telefone");
    private Label labEmail = new Label("E-mail:");
    private Label labCnpj = new Label("CNPJ");
    private Label labCpf = new Label("CPF:");
    private Label labRua = new Label("Rua:");
    private Label labNumero = new Label ("Número");
    private Label labBairro = new Label("Bairro:");
    private Label labCep = new Label("CEP:");
    private Label labCidade = new Label("Cidade:");
    private Label labUf = new Label("UF:");
    private Label labObservacoes = new Label("Observações:");
    private Label labTipo = new Label("Tipo:");


    private TextField tfdCodigo = new TextField();
    private TextField tfdDenominacaoSocial = new TextField();
    private TextField tfdNome = new TextField();
    private TextField tfdTelefone = new TextField();
    private TextField tfdEmail = new TextField();
    private TextField tfdCnpj = new TextField();
    private TextField tfdCpf = new TextField();
    private TextField tfdRua = new TextField();
    private TextField tfdNumero = new TextField();
    private TextField tfdBairro = new TextField();
    private TextField tfdCep = new TextField();
    private TextField tfdCidade = new TextField();
    private TextField tfdUf= new TextField();
    private TextField tfdObservacoes = new TextField();
    private TextField tfdTipo = new TextField();


    private Button btnSalva = new Button("Salvar");
    private Button btnEditar = new Button("Editar");

    private TableView tabela = new TableView<>();
    private TableColumn colunaCodigo = new TableColumn<>("Código");
    private TableColumn colunaDenominacaoSocial = new TableColumn<>("Denominação Social");
    private TableColumn colunaNome = new TableColumn<>("Nome");
    private TableColumn colunaTelefone = new TableColumn<>("Telefone");
    private TableColumn colunaEmail = new TableColumn<>("E-mail");
    private TableColumn colunaCnpj = new TableColumn<>("CNPJ");
    private TableColumn colunaCpf = new TableColumn<>("CPF");
    private TableColumn colunaRua = new TableColumn<>("Rua");
    private TableColumn colunaNumero = new TableColumn<>("Número");
    private TableColumn colunaBairro = new TableColumn<>("Bairro");
    private TableColumn colunaCep = new TableColumn<>("CEP");
    private TableColumn colunaCidade = new TableColumn<>("Cidade");
    private TableColumn colunaUf = new TableColumn<>("UF");
    private TableColumn colunaObservacoes = new TableColumn<>("Observações");
    private TableColumn colunaTipo = new TableColumn<>("Tipo");


    private GridPane telaPrincipal = new GridPane();
    private GridPane telaEsquerda = new GridPane();
    private GridPane telaDireita = new GridPane();
}
