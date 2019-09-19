package com.yard.stockmanager.tabs;

import com.yard.stockmanager.persistence.dao.DepartmentDAO;
import com.yard.stockmanager.persistence.entity.Departamento;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DepartmentRegisterTab extends Tab{
    private Stage stage;
    private Font font = new Font(14);

    public DepartmentRegisterTab()
    {
        super("Cadastro de Departamentos");
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

        //textArea
        tarDescricao.setEditable(true);
        tarDescricao.setFont(font);
        tarDescricao.setPrefSize(tarWidth,tarHeigt);


        //Botões
        //salvar
        btnSalva.setVisible(true);
        btnSalva.setPrefSize(100,30);
        //acao salvar
        btnSalva.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                salvar();
            }
        });

        //editar
        btnEditar.setVisible(true);
        btnEditar.setPrefSize(100,30);
        //acao editar
        btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editar();
            }
        });

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

    public void salvar(){


        try {
            dep.setNome(tfdDepartamento.getText());
            dep.setDescricao(tarDescricao.getText());

            depDAO.add(dep);

            //mensagem de confirmação
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Salvo");
            alert.setHeaderText("Departamento salvo com sucesso.");
            alert.show();

            //limpeza dos campos
            tfdDepartamento.setText("");
            tfdDepartamento.requestFocus();
            tarDescricao.setText("");

        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocorreu um erro ao salvar o registro");
            alert.setHeaderText("Houve um problema durante o salvamento do registro.");
            alert.show();
            throw new IllegalStateException(ex);//verificar tipo de exception
        }

    }

    public void editar(){
        System.out.println("editing");
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

    //botão salvar
    private Button btnSalva = new Button("Salvar");
    private Button btnEditar = new Button("Editar");

    //tabela
    private TableView tabela = new TableView<>();
    private TableColumn colunaCodigo = new TableColumn<>("Código");
    private TableColumn colunaDepartamento = new TableColumn<>("Departamento");
    private TableColumn colunaDescricao = new TableColumn<>("Descrição");

    //grid
    private GridPane telaPrincipal = new GridPane();
    private GridPane telaEsquerda = new GridPane();
    private GridPane telaDireita = new GridPane();

    //entidade
    private Departamento dep = new Departamento();

    //dao
    private DepartmentDAO depDAO = new DepartmentDAO();


}

