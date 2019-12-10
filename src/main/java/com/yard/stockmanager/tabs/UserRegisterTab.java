package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.parts.Window;
import com.yard.stockmanager.persistence.dao.*;
import javafx.scene.control.Button;
import com.yard.stockmanager.persistence.entity.*;
import com.yard.stockmanager.persistence.entity.Funcionario;
import com.yard.stockmanager.useful.Error;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.util.List;

public class UserRegisterTab extends ManagementTab<Funcionario> {

    private Object selected[] = new Object[10]; // variavel para alocar objeto trazido na seleção do registro da tabela superior
    private Object bottomSelected[] = new Object[10]; // variavel para alocar objeto trazido na seleção do registro da tabela inferior
    private int pessoaid; //variavel de marcação do id do produto
    private int enderecoid; //variavelde de marcação do id do endereço para um registro em edição. Usado para possivel retorno dos registros
    private int cidadeid;//

    public UserRegisterTab() {
        super("Cadastro de Usuario");
        initComponents();
    }
    //private List<Pessoa> pessoas = PessoaDAO.getOnllyAddres();


    @Override
    public void refresh() {
        UserRegisterDAO dao = new UserRegisterDAO();
        List<Funcionario> list = dao.getAll();

        tableView.setItems(FXCollections.observableArrayList( list ));
        tableView.refresh();

    }

    @Override
    public boolean validate() {
        String errors = "";

        if (tfdNomeUsuario.getText().isEmpty()) {
            errors = errors + "Nome do Usuário";
        }
        if (tfdSenha.getText().isEmpty()) {
            errors = errors + "Senha";
        }
        if (tfdSenha.getText().isEmpty()) {
            errors = errors + "Senha";
        }
        if (tfdEmail.getText().isEmpty()) {
            errors = errors + "Email";
        }

        if (tfdFuncao.getText().isEmpty()) {
            errors = errors + "Função";
        }
        if (tfdNivelAcesso.getText().isEmpty()) {
            errors = errors + "Nível de Acesso";
        }
        if (!errors.isEmpty())
        {
            Error.messageAndLog(errors);
        }

        return errors.isEmpty();
        //}
    }


    @Override
    public void save() {
        int idcidade;
        int idendereco;
        Pessoa pes = new Pessoa();
        Endereco end = new Endereco();
        Cidade cid = new Cidade();
        CidadeDAO cidDao = new CidadeDAO();
        EnderecoDAO endDao = new EnderecoDAO();
        cid.setNome(tfdCidade.getText());
        cid.setUf(tfdUf.getText());
        cid.setAtivo(tfdAtivo.getText().charAt(0));
        idcidade = cidDao.addCidId(cid);
        end.setCidade(cidDao.getById(idcidade));
        //end.setEndereco(tfdEndereco.getText());
        end.setRua(tfdRua.getText());
        end.setNumero(tfdNumero.getText());
        end.setCep(tfdCep.getText());
        end.setBairro(tfdBairro.getText());
        end.setAtivo(tfdAtivo1.getText().charAt(0));
        //end.setEndereco(endDao.endereco());
        idendereco = endDao.addEndId(end);
        pes.setEndereco(EnderecoDAO.getById(idendereco));
        pes.setDenominacaosocial(tfdDenominacaoSocial.getText());
        pes.setNome(tfdNome.getText());
        pes.setEmail(tfdEmail.getText());
        pes.setCnpj(tfdCnpj.getText());
        pes.setCpf(tfdCpf.getText());
        pes.setObservacoes(tfdObservacoes.getText());
        pes.setAtivo(tfdAtivo.getText().charAt(0));
        Funcionario fun = new Funcionario();
        Telefones tel = new Telefones();
        PessoaHasTelefones pht = new PessoaHasTelefones();
        pes.setEndereco(end);
        fun.setLogin(tfdNomeUsuario.getText());
        System.out.println("salvou");
        fun.setSenha(tfdSenha.getText());
        fun.setEmail(tfdEmail.getText());
        fun.setFuncao(tfdFuncao.getText());
        fun.setNivelacesso(tfdNivelAcesso.getText().charAt(0));
        pht.setTelefones(tel);


        UserRegisterDAO useDao = new UserRegisterDAO();
        useDao.add(fun);
        System.out.println("funcionou");
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
    public void setDadosPeople() {

        if ((pessoa = (Pessoa) window.getSelected()) != null) {

            tfdCodPessoa.setText(pessoa.getId() + "");
            tfdDenominacaoSocial.setText((pessoa.getDenominacaosocial()));
            tfdNome.setText(pessoa.getNome());
            tfdEmail1.setText(pessoa.getEmail());
            tfdCnpj.setText(pessoa.getCnpj());
            tfdCpf.setText(pessoa.getCpf());
            tfdObservacoes.setText(pessoa.getObservacoes());
            tfdAtivo.setText("S");
            tfdEndereco.setText("dsfsdf");
            tfdRua.setText(pessoa.getEndereco().getRua());
            tfdCep.setText(pessoa.getEndereco().getCep());
            tfdAtivo1.setText("S");
            tfdBairro.setText(pessoa.getEndereco().getBairro());
            tfdCidade.setText(pessoa.getEndereco().getCidade().getNome());
            tfdUf.setText(pessoa.getEndereco().getCidade().getUf());

        }

    }

    //Metodo de seleção dos registros da tabela inferior
    //public void selectBottom() {
        //if ((bottomSelected = (Object[]) getBottomSelected()) != null) {
        //    pessoaid = (int) tableViewBottom.getSelectionModel().getSelectedItem()[0];
      //      pessoa = PessoaDAO.getById(productId);
    //    }
    //}





    private void initComponents() {
        innerGrid.addRow(0, labCodPessoa, tfdCodPessoa, btnBuscarPes);
        innerGrid.addRow(1, labNomeUsuario, tfdNomeUsuario);
        innerGrid.addRow(2, labSenha, tfdSenha);
        innerGrid.addRow(3, labEmail, tfdEmail);
        innerGrid.addRow(4, labFuncao, tfdFuncao);
        innerGrid.addRow(5, labNivelAcesso, tfdNivelAcesso);
        innerGrid.addRow(6, hbxTitlePess);
        innerGrid.addRow(7, labDenominacaoSocial, tfdDenominacaoSocial);
        innerGrid.addRow(8,labNome, tfdNome);
        innerGrid.addRow(9, labTelefone, tfdTelefone);
        innerGrid.addRow(10,labEmail1, tfdEmail1);
        innerGrid.addRow(11,labCnpj, tfdCnpj);
        innerGrid.addRow(12,labCpf, tfdCpf);
        innerGrid.addRow(13,labObservacoes, tfdObservacoes);
        innerGrid.addRow(14,labAtivo, tfdAtivo);
        innerGrid.addRow(15, hbxTitleEnd);
        innerGrid.addRow(16,labCidade, tfdCidade);
        innerGrid.addRow(17,labUf, tfdUf);
        innerGrid.addRow(18,labEndereco, tfdEndereco);
        innerGrid.addRow(19,labRua, tfdRua);
        innerGrid.addRow(20,labCEP, tfdCep);
        innerGrid.addRow(21,labAtivo1, tfdAtivo1);
        innerGrid.addRow(22, labBairro, tfdBairro);



        TableColumn<Funcionario, Integer> id = new TableColumn<>("Código");
        TableColumn<Funcionario, String> nomeUsuario = new TableColumn<>("Nome Usuário");
        TableColumn<Funcionario, String> Senha = new TableColumn<>("Senha");
        TableColumn<Funcionario, String> Email = new TableColumn<>("Email");
        TableColumn<Funcionario, String> Funcao = new TableColumn<>("Função");
        TableColumn<Funcionario, Integer> nivelAcesso = new TableColumn<>("Nível de Acesso");

        tableView.getColumns().addAll(
                id,
                nomeUsuario,
                Senha,
                Email,
                Funcao,
                nivelAcesso
        );


        //busca de pessoas
        btnBuscarPes.setVisible(true);
        btnBuscarPes.setPrefSize(70, 10);


        //evento do botão BuscarPessoas
        btnBuscarPes.setOnAction(event -> {
            ManagementTab tab = new PeopleRegisterTab();

            window = new Window(getTabPane().getScene(), tab, "Busca de Pessoas");
            window.showAndWait();

            if (window.getSelected() != null) {
                setDadosPeople();
            }
        });



        refresh();
    }

    //Criação dos componentes da
    private Text txtTitlePess = new Text("Dados da pessoa");
    private HBox hbxTitlePess = new HBox(txtTitlePess);

    private Label labNomeUsuario = new Label("Login:");
    private Label labSenha = new Label("Senha:");
    private Label labEmail = new Label("E-mail:");
    private Label labFuncao = new Label("Função:");
    private Label labNivelAcesso = new Label("Nível de Acesso:");
    private Label labCodPessoa = new Label("Pessoa");

    private TextField tfdNomeUsuario = new TextField();
    private TextField tfdSenha = new TextField();
    private TextField tfdEmail = new TextField();
    private TextField tfdFuncao = new TextField();
    private TextField tfdNivelAcesso = new TextField();
    private TextField tfdCodPessoa = new TextField();

    private Text txtTitleEnd = new Text("Dados do Endereço");
    private HBox hbxTitleEnd = new HBox(txtTitleEnd);


    private Label labCidade = new Label ("Cidade:");
    private Label labUf = new Label ("UF:");
    private Label labEndereco = new Label ("Endereço:");
    private Label labRua = new Label ("Rua:");
    private Label labNumero = new Label ("Número:");
    private Label labCEP = new Label ("CEP:");
    private Label labAtivo1 = new Label ("Ativo:");
    private Label labBairro = new Label ("Bairro:");


    private TextField tfdCidade = new TextField();
    private TextField tfdUf = new TextField();
    private TextField tfdEndereco = new TextField();
    private TextField tfdRua = new TextField();
    private TextField tfdNumero = new TextField();
    private TextField tfdCep = new TextField();
    private TextField tfdAtivo1 = new TextField();
    private TextField tfdBairro = new TextField();

    private Label labDenominacaoSocial = new Label("Denominação Social");
    private Label labNome = new Label("Nome:");
    private Label labTelefone = new Label("Telefone");
    private Label labEmail1 = new Label("E-mail:");
    private Label labCnpj = new Label("CNPJ");
    private Label labCpf = new Label("CPF:");
    private Label labObservacoes = new Label("Observações:");
    private Label labAtivo = new Label("Ativo:");

    private TextField tfdDenominacaoSocial = new TextField();
    private TextField tfdNome = new TextField();
    private TextField tfdTelefone = new TextField();
    private TextField tfdEmail1 = new TextField();
    private TextField tfdCnpj = new TextField();
    private TextField tfdCpf = new TextField();
    private TextField tfdObservacoes = new TextField();
    private TextField tfdAtivo = new TextField();

    public HBox getHbxTitleEnd() {
        return hbxTitleEnd;
    }

    public void setHbxTitleEnd(HBox hbxTitleEnd) {
        this.hbxTitleEnd = hbxTitleEnd;
    }

    public HBox getHbxTitlePess() {
        return hbxTitlePess;
    }

    public void setHbxTitlePess(HBox hbxTitlePess) {
        this.hbxTitlePess = hbxTitlePess;
    }

    private Button btnBuscarPes = new Button("Buscar");

    //Entidade para as janelas de seleção
    private Window window;

    //variaveis para armazenamento de entidades
    private Pessoa pessoa = new Pessoa ();
}
