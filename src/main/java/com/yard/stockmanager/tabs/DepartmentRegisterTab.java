package com.yard.stockmanager.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DepartmentRegisterTab extends Tab{
    private Stage stage;
    private Font font = new Font(14);

    public DepartmentRegisterTab()
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
        labDepartamento.setFont(font);
        labDepartamento.setPrefSize(labWidth,labHeight);

        labDescricao.setFont(font);
        labDescricao.setPrefSize(labWidth,labHeight);



        //TextFields
        tfdDepartamento.setEditable(true);
        tfdDepartamento.setFont(font);
        tfdDepartamento.setPrefSize(tfdWidth,tfdHeight);

        tarDescricao.setEditable(true);
        tarDescricao.setFont(font);
        tarDescricao.setPrefSize(tarWidth,tarHeigt);


        //Botões
        btnSalva.setVisible(true);
        btnSalva.setPrefSize(100,30);
        //acoes dos botoes
        //salvar
        //btnSalva.setOnAction();
        btnEditar.setVisible(true);
        btnEditar.setPrefSize(100,30);

        //Colunas da tabela
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("Código"));
        colunaDepartamento.setCellValueFactory(new PropertyValueFactory<>("Departamento"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("Descrição"));


        //Tabela
        tabela.setPrefSize(1000, 1000);
        tabela.getColumns().addAll(colunaCodigo, colunaDepartamento, colunaDescricao);

        //Inicializa os paineis da tela
        telaPrincipal.setPadding(new Insets(0));
        telaEsquerda.setPadding(new Insets(100));
        telaDireita.setPadding(new Insets(100));

        //Inclui as Labels e TextFields nas linhas e colunas no painel da esquerda
        telaEsquerda.setAlignment(Pos.TOP_RIGHT);
        telaEsquerda.addRow(0, labDepartamento, tfdDepartamento);
        telaEsquerda.addRow(1, labDescricao, tarDescricao);
        telaEsquerda.add(btnSalva, 1, 2);

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
    private double tarWidth = 300;
    private double tarHeigt = 50;

    //Criação dos componentes da tela
    private Label labDepartamento = new Label("Departamento:");
    private Label labDescricao = new Label("Descrição:");


    private TextField tfdDepartamento = new TextField();
    private TextArea tarDescricao = new TextArea();


    private Button btnSalva = new Button("Salvar");
    private Button btnEditar = new Button("Editar");

    private TableView tabela = new TableView<>();
    private TableColumn colunaCodigo = new TableColumn<>("Código");
    private TableColumn colunaDepartamento = new TableColumn<>("Departamento");
    private TableColumn colunaDescricao = new TableColumn<>("Descrição");

    private GridPane telaPrincipal = new GridPane();
    private GridPane telaEsquerda = new GridPane();
    private GridPane telaDireita = new GridPane();

}

