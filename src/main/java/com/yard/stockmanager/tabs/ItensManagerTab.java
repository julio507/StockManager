package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.parts.Window;
import com.yard.stockmanager.persistence.dao.NfeDAO;
import com.yard.stockmanager.persistence.dao.NfeHasProdutoDAO;
import com.yard.stockmanager.persistence.entity.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.eclipse.jdt.internal.compiler.codegen.ConstantPool.ToString;

public class ItensManagerTab extends ManagementTab<Produto>
{

    public ItensManagerTab()
        {

            super("Saída de Produtos");
            initComponents();
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
        Nfe nfe = new Nfe();
        NfeDAO nfedao = new NfeDAO();
        Pessoa pes = new Pessoa();
        Endereco end = new Endereco();
        nfe.setId(1);
        nfe.setPessoaByPessoaremId(pes);
        nfe.setPessoaByPessoadestId(pes);
        nfe.setEnderecoByEnderecoremId(end);
        nfe.setEnderecoByEnderecodestId(end);
        nfe.setNumnf(454654);
        nfe.setTipo('1');
        nfe.setObservacoes("sdfsdf");
        nfe.setAtivo('1');







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
    public void setDadosProduct() {

        if ((produto = (Produto) window.getSelected()) != null) {

            tfdProduto.setText(produto.getId() + "");
            tfdData.setText("08/12/2019");
            tfdDepartamento.setText(produto.getDepartamento().getNome());
            tfdCategoria.setText(produto.getCategoria().getNome());
            tfdValorItem.setText(produto.getCustounitario().toString())
            ;

        }

    }



    private void initComponents()
        {
            innerGrid.addRow(0,labProduto, tfdProduto, btnBuscarProd);
            innerGrid.addRow(1,labNumeroNf, tfdNumeroNf);
            innerGrid.addRow(2,labData, tfdData);
            innerGrid.addRow(3,labQuantidade, tfdQuant);
            innerGrid.addRow(4,labDepartamento, tfdDepartamento);
            innerGrid.addRow(5,labCategoria, tfdCategoria);
            innerGrid.addRow(6,labValorItem, tfdValorItem);
            innerGrid.addRow(7,labPesRem, tfdPesRem);
            innerGrid.addRow(8,labEndRem, tfdEndRem);
            innerGrid.addRow(9,labPesDest, tfdPesDest);
            innerGrid.addRow(10,labEndDest, tfdEndDest);
            innerGrid.addRow(11,labValorTotal, tfdValorTotal);


            TableColumn<Produto, Integer> numeronf = new TableColumn<>("Número NF");
            TableColumn<Produto, Date> data = new TableColumn<>("Data");
            TableColumn<Produto, Integer> quantidade = new TableColumn<>("Quantidade");
            TableColumn<Produto, Integer> departamento = new TableColumn<>("Departamento");
            TableColumn<Produto, Integer> categoria = new TableColumn<>("Categoria");
            TableColumn<Produto, Double> valoritem = new TableColumn<>("Valor Unitário");
            TableColumn<Produto, Double> valortotal = new TableColumn<>("Valor Total");

            numeronf.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("numeronf"));
            data.setCellValueFactory(new PropertyValueFactory<Produto, Date>("data"));
            quantidade.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidade"));
            departamento.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("departamento"));
            categoria.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("categoria"));
            valoritem.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valoritem"));
            valortotal.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valortotal"));


            tableView.getColumns().addAll(
                    numeronf,
                    data,
                    quantidade,
                    departamento,
                    categoria,
                    valoritem,
                    valortotal
            );

            //busca de pessoas
            btnBuscarProd.setVisible(true);
            btnBuscarProd.setPrefSize(70, 10);


            //evento do botão BuscarPessoas
            btnBuscarProd.setOnAction(event -> {
                ManagementTab tab = new ProductRegisterTab();

                window = new Window(getTabPane().getScene(), tab, "Busca de Produtos");
                window.showAndWait();

                if (window.getSelected() != null) {
                    setDadosProduct();
                }
            });

            refresh();

        }


        //Criação dos componentes da tela
        private Label labNumeroNf = new Label("Número NF:");
        private Label labData = new Label("Data:");
        private Label labQuantidade = new Label("Quantidade:");
        private Label labDepartamento = new Label("Departamento:");
        private Label labCategoria = new Label("Categoria:");
        private Label labValorItem = new Label("Valor Unitário:");
        private Label labValorTotal = new Label("Valor Total:");
        private Label labProduto = new Label("Produto");
        private Label labPesRem = new Label ("Pessoa Remetente");
        private Label labEndRem = new Label ("Endereço Remetente");
        private Label labPesDest = new Label ("Pessoa Destino");
        private Label labEndDest = new Label ("Endereço Destino");



        private TextField tfdNumeroNf = new TextField();
        private TextField tfdData = new TextField();
        private TextField tfdQuant = new TextField();
        private TextField tfdDepartamento = new TextField();
        private TextField tfdCategoria = new TextField();
        private TextField tfdValorItem = new TextField();
        private TextField tfdValorTotal = new TextField();
        private TextField tfdProduto = new TextField();
        private TextField tfdPesRem = new TextField();
        private TextField tfdEndRem = new TextField();
        private TextField tfdPesDest = new TextField();
        private TextField tfdEndDest = new TextField();

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


    private Button btnBuscarProd = new Button();
    private Window window;
    private Produto produto = new Produto();


    }

