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

public class UserRegisterTab extends Tab {

    private Stage stage;
    private Font font = new Font(14);

    public UserRegisterTab()
    {
        super("Cadastro de Usuario");
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

        labNomeUsuario.setFont(font);
        labNomeUsuario.setPrefSize(labWidth,labHeight);

        labSenha.setFont(font);
        labSenha.setPrefSize(labWidth,labHeight);

        labNomeFuncionario.setFont(font);
        labNomeFuncionario.setPrefSize(labWidth,labHeight);

        labEmail.setFont(font);
        labEmail.setPrefSize(labWidth,labHeight);

        labTelefone.setFont(font);
        labTelefone.setPrefSize(labWidth,labHeight);

        labFuncao.setFont(font);
        labFuncao.setPrefSize(labWidth,labHeight);

        labNivelAcesso.setFont(font);
        labNivelAcesso.setPrefSize(labWidth,labHeight);


        //TextFields
        tfdCodigo.setEditable(true);
        tfdCodigo.setFont(font);
        tfdCodigo.setPrefSize(tfdWidth,tfdHeight);

        tfdNomeUsuario.setEditable(true);
        tfdNomeUsuario.setFont(font);
        tfdNomeUsuario.setPrefSize(tfdWidth,tfdHeight);

        tfdSenha.setEditable(true);
        tfdSenha.setFont(font);
        tfdSenha.setPrefSize(tfdWidth,tfdHeight);

        tfdNomeUsuario.setEditable(true);
        tfdNomeUsuario.setFont(font);
        tfdNomeUsuario.setPrefSize(tfdWidth,tfdHeight);

        tfdNomeFuncionario.setEditable(true);
        tfdNomeFuncionario.setFont(font);
        tfdNomeFuncionario.setPrefSize(tfdWidth,tfdHeight);

        tfdEmail.setEditable(true);
        tfdEmail.setFont(font);
        tfdEmail.setPrefSize(tfdWidth,tfdHeight);

        tfdTelefone.setEditable(true);
        tfdTelefone.setFont(font);
        tfdTelefone.setPrefSize(tfdWidth,tfdHeight);

        tfdTelefone.setEditable(true);
        tfdTelefone.setFont(font);
        tfdTelefone.setPrefSize(tfdWidth,tfdHeight);

        tfdFuncao.setEditable(true);
        tfdFuncao.setFont(font);
        tfdFuncao.setPrefSize(tfdWidth,tfdHeight);

        tfdNivelAcesso.setEditable(true);
        tfdNivelAcesso.setFont(font);
        tfdNivelAcesso.setPrefSize(tfdWidth,tfdHeight);

        //Botões
        btnSalva.setVisible(true);
        btnSalva.setPrefSize(100,30);
        btnEditar.setVisible(true);
        btnEditar.setPrefSize(100,30);

        //Colunas da tabela
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("Código"));
        colunaNomeUsuario.setCellValueFactory(new PropertyValueFactory<>("Login"));
        colunaSenha.setCellValueFactory(new PropertyValueFactory<>("Senha"));
        colunaNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("Nome do Funcionário"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory<>("E-mail"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        colunaFuncao.setCellValueFactory(new PropertyValueFactory<>("Função"));
        colunaNivelAcesso.setCellValueFactory(new PropertyValueFactory<>("Nível Acesso"));

        //Tabela
        tabela.setPrefSize(1000, 1000);
        tabela.getColumns().addAll(colunaCodigo, colunaNomeUsuario, colunaSenha, colunaNomeFuncionario, colunaEmail, colunaTelefone, colunaFuncao, colunaNivelAcesso);

        //Inicializa os paineis da tela
        telaPrincipal.setPadding(new Insets(0));
        telaEsquerda.setPadding(new Insets(100));
        telaDireita.setPadding(new Insets(100));

        //Inclui as Labels e TextFields nas linhas e colunas no painel da esquerda
        telaEsquerda.setAlignment(Pos.TOP_RIGHT);
        telaEsquerda.addRow(0, labCodigo, tfdCodigo);
        telaEsquerda.addRow(1, labNomeUsuario, tfdNomeUsuario);
        telaEsquerda.addRow(2, labSenha, tfdSenha);
        telaEsquerda.addRow(3, labNomeFuncionario, tfdNomeFuncionario);
        telaEsquerda.addRow(4, labEmail, tfdEmail);
        telaEsquerda.addRow(5, labTelefone, tfdTelefone);
        telaEsquerda.addRow(6, labFuncao, tfdFuncao);
        telaEsquerda.addRow(7, labNivelAcesso, tfdNivelAcesso);
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
    private Label labNomeUsuario = new Label("Login:");
    private Label labSenha = new Label("Senha:");
    private Label labNomeFuncionario = new Label("Nome do Funcionário");
    private Label labEmail = new Label("E-mail:");
    private Label labTelefone = new Label("Telefone:");
    private Label labFuncao = new Label("Função:");
    private Label labNivelAcesso = new Label("Nível de Acesso:");

    private TextField tfdCodigo = new TextField();
    private TextField tfdNomeUsuario = new TextField();
    private TextField tfdSenha = new TextField();
    private TextField tfdNomeFuncionario = new TextField();
    private TextField tfdEmail = new TextField();
    private TextField tfdTelefone = new TextField();
    private TextField tfdFuncao = new TextField();
    private TextField tfdNivelAcesso = new TextField();

    private Button btnSalva = new Button("Salvar");
    private Button btnEditar = new Button("Editar");

    private TableView tabela = new TableView<>();
    private TableColumn colunaCodigo = new TableColumn<>("Código");
    private TableColumn colunaNomeUsuario = new TableColumn<>("Login");
    private TableColumn colunaSenha = new TableColumn<>("Senha");
    private TableColumn colunaNomeFuncionario = new TableColumn<>("Nome do Funcionário");
    private TableColumn colunaEmail = new TableColumn<>("E-mail");
    private TableColumn colunaTelefone = new TableColumn<>("Telefone");
    private TableColumn colunaFuncao = new TableColumn<>("Função");
    private TableColumn colunaNivelAcesso = new TableColumn<>("Nível de Acesso");

    private GridPane telaPrincipal = new GridPane();
    private GridPane telaEsquerda = new GridPane();
    private GridPane telaDireita = new GridPane();
}
