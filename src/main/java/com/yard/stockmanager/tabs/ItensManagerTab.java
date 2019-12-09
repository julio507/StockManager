package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.parts.Window;
import com.yard.stockmanager.persistence.dao.EnderecoDAO;
import com.yard.stockmanager.persistence.dao.NfeDAO;
import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.entity.Nfe;
import com.yard.stockmanager.persistence.entity.Pessoa;
import com.yard.stockmanager.persistence.entity.Produto;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.Date;

public class ItensManagerTab extends ManagementTab<Produto> {

    private Produto produto = new Produto();
    private Pessoa rem = new Pessoa();
    private Pessoa dest = new Pessoa();

    public ItensManagerTab() {

        super("Saída de Produtos");
        tfdData.setText(new java.util.Date().toString());
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

            tfdProdutoId.setText(produto.getId() + "");
            tfdProduto.setText(produto.getNome());
            tfdMarcaProd.setText(produto.getMarca().getNome());
            tfdDepartamento.setText(produto.getDepartamento().getNome());
            tfdCategoria.setText(produto.getCategoria().getNome());
            tfdUnidade.setText(produto.getUnidade().getSigla());
            tfdValorItem.setText(produto.getCustounitario() + "");
        }

    }

    public void setDadosRem() {
        if ((rem = (Pessoa) window.getSelected()) != null) {
            if (!rem.getNome().equals("")) {
                tfdPesRem.setText(rem.getNome());
            }else{
                tfdPesRem.setText(rem.getDenominacaosocial());
            }
            if (!rem.getCpf().equals("")) {
                tfdCpfCnpjRem.setText(rem.getCpf());
            }else{
                tfdCpfCnpjRem.setText(rem.getCnpj());
            }
            tfdEndRem.setText(EnderecoDAO.getById(rem.getEndereco().getId()).toString());
        }
    }

    public void setDadosDest() {
        if ((dest = (Pessoa) window.getSelected()) != null) {
            if (!dest.getNome().equals("")) {
                tfdPesDest.setText(dest.getNome());
            }else{
                tfdPesDest.setText(dest.getDenominacaosocial());
            }
            if (!dest.getCpf().equals("")) {
                tfdCpfCnpjDest.setText(dest.getCpf());
            }else{
                tfdCpfCnpjDest.setText(dest.getCnpj());
            }
            tfdEndDest.setText(EnderecoDAO.getById(dest.getEndereco().getId()).toString());
        }

    }


    private void initComponents() {
        //Text
        txtTitleProd.setFont(Font.font("System", FontWeight.BOLD, 20));
        txtTitleEndDest.setFont(Font.font("System", FontWeight.BOLD, 20));
        txtTitleEndRem.setFont(Font.font("System", FontWeight.BOLD, 20));

        //componentes das infos do produto
        //TextFields produto
        tfdProdutoId.setEditable(false);
        tfdProdutoId.setText("--selecione o Produto--");
        tfdProduto.setEditable(false);
        tfdMarcaProd.setEditable(false);
        tfdDepartamento.setEditable(false);
        tfdCategoria.setEditable(false);
        tfdUnidade.setEditable(false);
        tfdValorItem.setEditable(false);

        //população dos campos na tela
        innerGrid.addRow(0, labNumeroNf, tfdNumeroNf);
        innerGrid.addRow(1, labData, tfdData);
        innerGrid.add(hbxTitleEndRem, 0, 2, 2, 1);
        innerGrid.addRow(3, labPesRem, tfdPesRem, btnBuscarRem);
        innerGrid.addRow(4, labCpfCnpjRem, tfdCpfCnpjRem);
        innerGrid.addRow(5, labEndRem, tfdEndRem);
        innerGrid.add(hbxTitleEndDest, 0, 6, 2, 1);
        innerGrid.addRow(7, labPesDest, tfdPesDest, btnBuscarDest);
        innerGrid.addRow(8, labCpfCnpjDest, tfdCpfCnpjDest);
        innerGrid.addRow(9, labEndDest, tfdEndDest);
        innerGrid.add(hbxTitleProd, 0, 10, 2, 1);
        innerGrid.addRow(11, labProdutoId, tfdProdutoId, btnBuscarProd);
        innerGrid.addRow(12, labProduto, tfdProduto);
        innerGrid.addRow(13, labMarcaProd, tfdMarcaProd);
        innerGrid.addRow(14, labDepartamento, tfdDepartamento);
        innerGrid.addRow(15, labCategoria, tfdCategoria);
        innerGrid.addRow(16, labValorItem, tfdValorItem);
        innerGrid.addRow(17, labUnidade, tfdUnidade);
        innerGrid.addRow(18, labValorTotal, tfdValorTotal);


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

        //busca de produtos
        btnBuscarProd.setVisible(true);
        btnBuscarProd.setPrefSize(70, 10);

        //evento do botão busca de produtos
        btnBuscarProd.setOnAction(event -> {
            ManagementTab tab = new ProductRegisterTab();

            window = new Window(getTabPane().getScene(), tab, "Busca de Produtos");
            window.showAndWait();

            if (window.getSelected() != null) {
                setDadosProduct();
            }
        });

        //busca de Remetentes
        btnBuscarRem.setVisible(true);
        btnBuscarRem.setPrefSize(70, 10);

        //evento do botão busca de remetente
        btnBuscarRem.setOnAction(event -> {
            ManagementTab tab = new PeopleRegisterTab();

            window = new Window(getTabPane().getScene(), tab, "Busca de Pessoas");
            window.showAndWait();

            if (window.getSelected() != null) {
                setDadosRem();
            }
        });

        //busca de Destinatarios
        btnBuscarDest.setVisible(true);
        btnBuscarDest.setPrefSize(70, 10);

        //evento do botão busca de destinatario
        btnBuscarDest.setOnAction(event -> {
            ManagementTab tab = new PeopleRegisterTab();

            window = new Window(getTabPane().getScene(), tab, "Busca de Pessoas");
            window.showAndWait();

            if (window.getSelected() != null) {
                setDadosDest();
            }
        });

        refresh();

    }


    //Criação dos componentes da tela
    //dados nfe
    private Label labNumeroNf = new Label("Número NF:");
    private Label labData = new Label("Data:");
    private Label labPesRem = new Label("Remetente");
    private Label labCpfCnpjRem = new Label("CPF/CNPJ");
    private Label labEndRem = new Label("Endereço");
    private Label labPesDest = new Label("Destino");
    private Label labCpfCnpjDest = new Label("CPF/CNPJ");
    private Label labEndDest = new Label("Endereço");

    //dados do produto
    private Label labProdutoId = new Label("Código:");
    private Label labProduto = new Label("Produto:");
    private Label labMarcaProd = new Label("Marca:");
    private Label labDepartamento = new Label("Departamento:");
    private Label labCategoria = new Label("Categoria:");
    private Label labUnidade = new Label("Unidade:");
    private Label labValorItem = new Label("Valor Unitário:");
    private Label labValorTotal = new Label("Valor Total:");


    private TextField tfdNumeroNf = new TextField();
    private TextField tfdData = new TextField();
    private TextField tfdPesRem = new TextField();
    private TextField tfdCpfCnpjRem = new TextField();
    private TextField tfdEndRem = new TextField();
    private TextField tfdPesDest = new TextField();
    private TextField tfdCpfCnpjDest = new TextField();
    private TextField tfdEndDest = new TextField();

    private TextField tfdProdutoId = new TextField();
    private TextField tfdProduto = new TextField();
    private TextField tfdMarcaProd = new TextField();
    private TextField tfdDepartamento = new TextField();
    private TextField tfdCategoria = new TextField();
    private TextField tfdUnidade = new TextField();
    private TextField tfdValorItem = new TextField();
    private TextField tfdValorTotal = new TextField();


    //Componentes Produto
    private Text txtTitleProd = new Text("Dados do Produto");
    private HBox hbxTitleProd = new HBox(txtTitleProd);

    private Text txtTitleEndRem = new Text("Dados do Remetente");
    private HBox hbxTitleEndRem = new HBox(txtTitleEndRem);

    private Text txtTitleEndDest = new Text("Dados do Destinatario");
    private HBox hbxTitleEndDest = new HBox(txtTitleEndDest);

    //botoes
    private Button btnBuscarProd = new Button("Buscar");
    private Button btnBuscarRem = new Button("Buscar");
    private Button btnBuscarDest = new Button("Buscar");
    private Window window;
}

