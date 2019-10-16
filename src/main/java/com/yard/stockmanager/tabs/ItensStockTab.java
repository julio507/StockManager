package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.entity.EstoqueHasProduto;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ItensStockTab extends ManagementTab<EstoqueHasProduto> {

    public ItensStockTab() {
        super("Cadastro de Itens por Estoque");
        initComponents();
    }

    @Override
    public void refresh() {

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

    }

    @Override
    public void clear() {

    }

    private void initComponents() {

        //Text
        txtTitleEstq.setFont(Font.font("System", FontWeight.BOLD, 20));

        //TextFields
        tfdCodEstq.setEditable(false);
        tfdCodEstq.setText("--selecione o Estoque--");
        tfdNomeEstq.setEditable(false);
        tfdEnderecoEstq.setEditable(false);
        tfdRuaEstq.setEditable(false);
        tfdBairroEstq.setEditable(false);

        //componentes das infos do produto
        //Text
        txtTitleProd.setFont(Font.font("System", FontWeight.BOLD, 20));

        //TextFields
        tfdCodProd.setEditable(false);
        tfdCodProd.setText("--selecione o Produto--");
        tfdNomeProd.setEditable(false);
        tfdMarcaProd.setEditable(false);
        tfdDepartamentoProd.setEditable(false);
        tfdCategoriaProd.setEditable(false);
        tfdUnidadeProd.setEditable(false);
        tfdValorProd.setEditable(false);


        //Inclui as Labels e TextFields nas linhas e colunas no painel da esquerda
        //alinhamento
        innerGrid.setAlignment(Pos.TOP_RIGHT);
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
        btnBuscarEstq.setVisible(true);
        btnBuscarEstq.setPrefSize(70, 30);
        btnBuscarProd.setVisible(true);
        btnBuscarProd.setPrefSize(70, 30);
        btnBuscarProd.setDisable(true);


        TableColumn<EstoqueHasProduto, Integer>id = new TableColumn<>("Cod. Prod.");
        TableColumn<EstoqueHasProduto, String> nome = new TableColumn<>("Prod.");
        TableColumn<EstoqueHasProduto, String> marca = new TableColumn<>("Marca");
        TableColumn<EstoqueHasProduto, String> departamento = new TableColumn<>("Departamento");
        TableColumn<EstoqueHasProduto, String> categoria = new TableColumn<>("Categoria");
        TableColumn<EstoqueHasProduto, Double> unidade = new TableColumn<>("Unidade");
        TableColumn<EstoqueHasProduto, Double> valor = new TableColumn<>("Valor");
        //Colunas da tabela
        id.setCellValueFactory(new PropertyValueFactory<EstoqueHasProduto, Integer>("Cod. Prod."));
        nome.setCellValueFactory(new PropertyValueFactory<EstoqueHasProduto, String>("Prod."));
        marca.setCellValueFactory(new PropertyValueFactory<EstoqueHasProduto, String>("Marca"));
        departamento.setCellValueFactory(new PropertyValueFactory<EstoqueHasProduto, String>("Departamento"));
        categoria.setCellValueFactory(new PropertyValueFactory<EstoqueHasProduto, String>("Categoria"));
        unidade.setCellValueFactory(new PropertyValueFactory<EstoqueHasProduto, Double>("Unidade"));
        valor.setCellValueFactory(new PropertyValueFactory<EstoqueHasProduto, Double>("valor"));

        //Tabela
        tableView.getColumns().addAll(
                id,
                nome,
                marca,
                departamento,
                categoria,
                unidade,
                valor
        );

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

