package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.dao.CidadeDAO;
import com.yard.stockmanager.persistence.dao.EnderecoDAO;
import com.yard.stockmanager.persistence.dao.PeopleRegisterDAO;
import com.yard.stockmanager.persistence.entity.Cidade;
import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.entity.Pessoa;
import com.yard.stockmanager.persistence.entity.Telefones;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javax.swing.*;
import java.util.List;

public class PeopleRegisterTab extends ManagementTab<Pessoa> {
    private Pessoa selected;

    public PeopleRegisterTab() {
        super("Cadastro de Pessoas");
        initComponents();
    }

    @Override
    public void refresh() {
        PeopleRegisterDAO dao = new PeopleRegisterDAO();

        List<Pessoa> list = dao.getAll();

        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();

    }

    @Override
    public boolean validate() {
        String errors = "";


        if (tfdDenominacaoSocial.getText().isEmpty()) {
            errors = errors + "Denominação Social";
        }
        if (tfdNome.getText().isEmpty()) {
            errors = errors + "Nome";
        }
        if (tfdTelefone.getText().isEmpty()) {
            errors = errors + "Telefone";
        }
        if (tfdEmail.getText().isEmpty()) {
            errors = errors + "Email";
        }
        if (tfdCnpj.getText().isEmpty()) {
            errors = errors + "CNPJ";
        }
        if (tfdCpf.getText().isEmpty()) {
            errors = errors + "CPF";
        }
        if (tfdObservacoes.getText().isEmpty()) {
            errors = errors + "Observações";
        }
        if (tfdAtivo.getText().isEmpty()) {
            errors = errors + "Ativo";
        }
        return errors.isEmpty();
    }

    @Override
    public void save() {
        int idendereco;
        int idcidade;
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
        end.setEndereco(endDao.endereco());
        idendereco = endDao.addEndId(end);
        pes.setEndereco(EnderecoDAO.getById(idendereco));
        pes.setDenominacaosocial(tfdDenominacaoSocial.getText());
        pes.setNome(tfdNome.getText());
        pes.setEmail(tfdEmail.getText());
        pes.setCnpj(tfdCnpj.getText());
        pes.setCpf(tfdCpf.getText());
        pes.setObservacoes(tfdObservacoes.getText());
        pes.setAtivo(tfdAtivo.getText().charAt(0));

        PeopleRegisterDAO pesDao = new PeopleRegisterDAO();
        pesDao.add(pes);
        System.out.println("Salvou");
        refresh();
    }

    @Override
    public void edit() {
        Pessoa pes = new Pessoa();
        Endereco end = new Endereco();

        pes.setId(this.id);
        pes.setDenominacaosocial(tfdDenominacaoSocial.getText());
        pes.setNome(tfdNome.getText());
        pes.setEmail(tfdEmail.getText());
        pes.setCnpj(tfdCnpj.getText());
        pes.setCpf(tfdCpf.getText());
        pes.setObservacoes(tfdObservacoes.getText());
        pes.setEndereco(end);
//        falta telefones / tipos

        PeopleRegisterDAO pesDao = new PeopleRegisterDAO();
        pesDao.update(pes);
        System.out.println("Edita");

        refresh();

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
        txtTitlePess.setFont(Font.font("System", FontWeight.BOLD, 20));
        txtTitleEnd.setFont(Font.font("System", FontWeight.BOLD, 20));

        innerGrid.addRow(0, hbxTitlePess);
        innerGrid.addRow(2,labDenominacaoSocial, tfdDenominacaoSocial);
        innerGrid.addRow(3,labNome, tfdNome);
        innerGrid.addRow(4,labTelefone, tfdTelefone);
        innerGrid.addRow(5,labEmail, tfdEmail);
        innerGrid.addRow(6,labCnpj, tfdCnpj);
        innerGrid.addRow(7,labCpf, tfdCpf);
        innerGrid.addRow(8,labObservacoes, tfdObservacoes);
        innerGrid.addRow(9,labAtivo, tfdAtivo);
        innerGrid.addRow(11, hbxTitleEnd);
        innerGrid.addRow(13,labCidade, tfdCidade);
        innerGrid.addRow(14,labUf, tfdUf);
        innerGrid.addRow(15,labEndereco, tfdEndereco);
        innerGrid.addRow(16,labRua, tfdRua);
        innerGrid.addRow(17,labCEP, tfdCep);
        innerGrid.addRow(18,labAtivo1, tfdAtivo1);
        innerGrid.addRow(19, labBairro, tfdBairro);







        TableColumn<Pessoa, Integer> id = new TableColumn<>("Código");
        TableColumn<Pessoa, String> denominaçãoSocial = new TableColumn<>("Denominação Social");
        TableColumn<Pessoa, String> nome = new TableColumn<>("Nome");
        TableColumn<Pessoa, String> telefone = new TableColumn<>("Telefone");
        TableColumn<Pessoa, String> email = new TableColumn<>("E-mail");
        TableColumn<Pessoa, String> cnpj = new TableColumn<>("Cnpj");
        TableColumn<Pessoa, String> cpf = new TableColumn<>("Cpf");
        TableColumn<Pessoa, String> observacoes = new TableColumn<>("Observações");
        TableColumn<Pessoa, Character> ativo = new TableColumn<>("Ativo");

        id.setCellValueFactory(new PropertyValueFactory<Pessoa, Integer>("id"));
        denominaçãoSocial.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("denominacaosocial"));
        nome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
        telefone.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("telefone"));
        email.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("email"));
        cnpj.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cnpj"));
        cpf.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("cpf"));
        observacoes.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("observacoes"));
        ativo.setCellValueFactory(new PropertyValueFactory<Pessoa, Character>("ativo"));

        tableView.getColumns().addAll(
                id,
                denominaçãoSocial,
                nome,
                telefone,
                email,
                cnpj,
                cpf,
                observacoes,
                ativo
        );

        refresh();
    }

    private int id;

    //Criação dos componentes da tela
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

    private Text txtTitlePess = new Text("Dados da Pessoa");
    private HBox hbxTitlePess = new HBox(txtTitlePess);
    private Label labDenominacaoSocial = new Label("Denominação Social");
    private Label labNome = new Label("Nome:");
    private Label labTelefone = new Label("Telefone");
    private Label labEmail = new Label("E-mail:");
    private Label labCnpj = new Label("CNPJ");
    private Label labCpf = new Label("CPF:");
    private Label labObservacoes = new Label("Observações:");
    private Label labAtivo = new Label("Ativo:");

    private TextField tfdDenominacaoSocial = new TextField();
    private TextField tfdNome = new TextField();
    private TextField tfdTelefone = new TextField();
    private TextField tfdEmail = new TextField();
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
}
