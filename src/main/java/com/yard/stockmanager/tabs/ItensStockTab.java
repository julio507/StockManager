package com.yard.stockmanager.tabs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ItensStockTab extends Tab {
    private Stage stage;
    private Font font = new Font(14);

    public ItensStockTab() {
        super("Cadastro de Itens por Estoque");
        initComponents();
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

    public Stage getStage() {
        return stage;
    }


    private void initComponents() {
        //Componentes da infos do estoque
        //debug
//        telaEsquerda.setGridLinesVisible(true);
//        telaDireita.setGridLinesVisible(true);
//        telaPrincipal.setGridLinesVisible(true);

        //Text
        txtTitleEstq.setFont(Font.font("System", FontWeight.BOLD, 20));

        // Labels
        labCodEstq.setFont(font);
        labCodEstq.setPrefSize(labWidth, labHeight);
        labNomeEstq.setFont(font);
        labNomeEstq.setPrefSize(labWidth, labHeight);
        labEnderecoEstq.setFont(font);
        labEnderecoEstq.setPrefSize(labWidth, labHeight);
        labRuaEstq.setFont(font);
        labRuaEstq.setPrefSize(labWidth, labHeight);
        labBairroEstq.setFont(font);
        labBairroEstq.setPrefSize(labWidth, labHeight);

        //TextFields
        tfdCodEstq.setEditable(false);
        tfdCodEstq.setFont(font);
        tfdCodEstq.setText("--selecione o Estoque--");
        tfdCodEstq.setPrefSize(tfdWidth, tfdHeight);
        tfdNomeEstq.setEditable(false);
        tfdNomeEstq.setFont(font);
        tfdNomeEstq.setPrefSize(tfdWidth, tfdHeight);
        tfdEnderecoEstq.setEditable(false);
        tfdEnderecoEstq.setFont(font);
        tfdEnderecoEstq.setPrefSize(tfdWidth, tfdHeight);
        tfdRuaEstq.setEditable(false);
        tfdRuaEstq.setFont(font);
        tfdRuaEstq.setPrefSize(tfdWidth, tfdHeight);
        tfdBairroEstq.setEditable(false);
        tfdBairroEstq.setFont(font);
        tfdBairroEstq.setPrefSize(tfdWidth, tfdHeight);

        //componentes das infos do produto
        //Text
        txtTitleProd.setFont(Font.font("System", FontWeight.BOLD, 20));

        //Labels
        labCodProd.setFont(font);
        labCodProd.setPrefSize(labWidth, labHeight);
        lanNomeProd.setFont(font);
        lanNomeProd.setPrefSize(labWidth, labHeight);
        labMarcaProd.setFont(font);
        labMarcaProd.setPrefSize(labWidth, labHeight);
        labDepartamentoProd.setFont(font);
        labDepartamentoProd.setPrefSize(labWidth, labHeight);
        labCategoriaProd.setFont(font);
        labCategoriaProd.setPrefSize(labWidth, labHeight);
        labUnidadeProd.setFont(font);
        labUnidadeProd.setPrefSize(labWidth, labHeight);
        labValorProd.setFont(font);
        labValorProd.setPrefSize(labWidth, labHeight);

        //TextFields
        tfdCodProd.setEditable(false);
        tfdCodProd.setFont(font);
        tfdCodProd.setPrefSize(tfdWidth, tfdHeight);
        tfdCodProd.setText("--selecione o Produto--");
        tfdNomeProd.setEditable(false);
        tfdNomeProd.setFont(font);
        tfdNomeProd.setPrefSize(tfdWidth, tfdHeight);
        tfdMarcaProd.setEditable(false);
        tfdMarcaProd.setFont(font);
        tfdMarcaProd.setPrefSize(tfdWidth, tfdHeight);
        tfdDepartamentoProd.setEditable(false);
        tfdDepartamentoProd.setFont(font);
        tfdDepartamentoProd.setPrefSize(tfdWidth, tfdHeight);
        tfdCategoriaProd.setEditable(false);
        tfdCategoriaProd.setFont(font);
        tfdCategoriaProd.setPrefSize(tfdWidth, tfdHeight);
        tfdUnidadeProd.setEditable(false);
        tfdUnidadeProd.setFont(font);
        tfdUnidadeProd.setPrefSize(tfdWidth, tfdHeight);
        tfdValorProd.setEditable(false);
        tfdValorProd.setFont(font);
        tfdValorProd.setPrefSize(tfdWidth, tfdHeight);

        //Botões
        btnSalva.setVisible(true);
        btnSalva.setPrefSize(100, 30);
        btnRemover.setVisible(true);
        btnRemover.setPrefSize(100, 30);
        btnBuscarEstq.setVisible(true);
        btnBuscarEstq.setPrefSize(70, 30);
        btnBuscarProd.setVisible(true);
        btnBuscarProd.setPrefSize(70, 30);
        btnBuscarProd.setDisable(true);

        //Colunas da tabela
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("Cod. Prod."));
        colunaNomeProd.setCellValueFactory(new PropertyValueFactory<>("Prod."));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        colunaDepartamento.setCellValueFactory(new PropertyValueFactory<>("Departamento"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        colunaUnidade.setCellValueFactory(new PropertyValueFactory<>("Unidade"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        //Tabela
        tabela.setPrefSize(900, 900);
        tabela.getColumns().addAll(colunaCodigo, colunaNomeProd, colunaMarca, colunaDepartamento, colunaCategoria, colunaUnidade, colunaValor);

        //Inicializa os paineis da tela
        telaPrincipal.setPadding(new Insets(0));
        telaEsquerda.setPadding(new Insets(100));
        telaDireita.setPadding(new Insets(100));

        //Inclui as Labels e TextFields nas linhas e colunas no painel da esquerda
        //alinhamento
        telaEsquerda.setAlignment(Pos.TOP_RIGHT);
        //dados do estoque
        telaEsquerda.addRow(0, hbxTitleEstq);
        telaEsquerda.addRow(1, labCodEstq, tfdCodEstq, btnBuscarEstq);
        telaEsquerda.addRow(2, labNomeEstq, tfdNomeEstq);
        telaEsquerda.addRow(3, labEnderecoEstq, tfdEnderecoEstq);
        telaEsquerda.addRow(4, labRuaEstq, tfdRuaEstq);
        telaEsquerda.addRow(5, labBairroEstq, tfdBairroEstq);
        //dados do produto
        telaEsquerda.addRow(6, hbxTitleProd);
        telaEsquerda.addRow(7, labCodProd, tfdCodProd, btnBuscarProd);
        telaEsquerda.addRow(8, lanNomeProd, tfdNomeProd);
        telaEsquerda.addRow(9, labMarcaProd, tfdMarcaProd);
        telaEsquerda.addRow(10, labDepartamentoProd, tfdDepartamentoProd);
        telaEsquerda.addRow(11, labCategoriaProd, tfdCategoriaProd);
        telaEsquerda.addRow(12, labUnidadeProd, tfdUnidadeProd);
        telaEsquerda.addRow(13, labValorProd, tfdValorProd);
        //botões
        telaEsquerda.add(btnSalva,1,14);

        //inclui a tabela e os botoes na tela da direita
        telaDireita.setAlignment(Pos.TOP_RIGHT);
        telaDireita.add(tabela, 0, 0, 2, 1);
        telaDireita.addRow(1, btnRemover, btnFinalizar);


        telaPrincipal.addRow(0, telaEsquerda, telaDireita);

        setContent(telaPrincipal);

    }

    //Iniciação das variaveis
    private double labWidth = 240;
    private double labHeight = 50;
    private double tfdWidth = 300;
    private double tfdHeight = 20;

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
    private Label lanNomeProd = new Label("Produto:");
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

    private Button btnSalva = new Button("Adicionar");
    private Button btnRemover = new Button("Remover");
    private Button btnFinalizar = new Button("Finalizar");
    private Button btnBuscarEstq = new Button("Buscar");
    private Button btnBuscarProd = new Button("Buscar");

    private TableView tabela = new TableView<>();
    private TableColumn colunaCodigo = new TableColumn<>("Cod. Prod.");
    private TableColumn colunaNomeProd = new TableColumn<>("Prod.");
    private TableColumn colunaMarca = new TableColumn<>("Marca");
    private TableColumn colunaDepartamento = new TableColumn<>("Departamento");
    private TableColumn colunaCategoria = new TableColumn<>("Categoria");
    private TableColumn colunaUnidade = new TableColumn<>("Unidade");
    private TableColumn colunaValor = new TableColumn<>("Valor");

    private GridPane telaPrincipal = new GridPane();
    private GridPane telaEsquerda = new GridPane();
    private GridPane telaDireita = new GridPane();
}

//}
