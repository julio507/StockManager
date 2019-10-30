package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.dao.PeopleRegisterDAO;
import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.entity.Pessoa;
import com.yard.stockmanager.persistence.entity.Telefones;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.List;

public class PeopleRegisterTab extends ManagementTab<Object> {
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

        if (tfdCodigo.getText().isEmpty()) {
            errors = errors + "Código";
        }

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
        if (tfdRua.getText().isEmpty()) {
            errors = errors + "Rua";
        }
        if (tfdNumero.getText().isEmpty()) {
            errors = errors + "Número";
        }
        if (tfdBairro.getText().isEmpty()) {
            errors = errors + "Bairro";
        }
        if (tfdCep.getText().isEmpty()) {
            errors = errors + "CEP";
        }
        if (tfdCidade.getText().isEmpty()) {
            errors = errors + "Cidade";
        }
        if (tfdUf.getText().isEmpty()) {
            errors = errors + "UF";
        }
        if (tfdObservacoes.getText().isEmpty()) {
            errors = errors + "Observações";
        }
        if (tfdTipo.getText().isEmpty()) {
            errors = errors + "Tipo";
        }
        return errors.isEmpty();
    }

    @Override
    public void save() {
        Pessoa pes = new Pessoa();
        Endereco end = new Endereco();
        Telefones tel = new Telefones();

        pes.setId(1);
        pes.setDenominacaosocial(tfdDenominacaoSocial.getText());
        pes.setNome(tfdNome.getText());
        pes.setEmail(tfdEmail.getText());
        pes.setCnpj(tfdCnpj.getText());
        pes.setCpf(tfdCpf.getText());
        pes.setObservacoes(tfdObservacoes.getText());
        pes.setEndereco(end);
//        falta combobox dos telefones/tipos

        PeopleRegisterDAO pesDao = new PeopleRegisterDAO();
        pesDao.add(pes);
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
        selected = (Pessoa) getSelected();

        if (selected != null) {
            tfdDenominacaoSocial.setText(selected.getDenominacaosocial());
            tfdNome.setText(selected.getNome());
            tfdEmail.setText(selected.getEmail());
            tfdCnpj.setText(selected.getCnpj());
            tfdCpf.setText(selected.getCpf());
            tfdObservacoes.setText(selected.getObservacoes());
            id = selected.getId();
            // falta telefone/tipo
        }
        else
            {
            clear();
        }
    }




    @Override
    public void clear() {

    }

    private void initComponents() {


        innerGrid.addRow(0,labCodigo, tfdCodigo);
        innerGrid.addRow(1,labDenominacaoSocial, tfdDenominacaoSocial);
        innerGrid.addRow(2,labNome, tfdNome);
        innerGrid.addRow(3,labTelefone, tfdTelefone);
        innerGrid.addRow(4,labEmail, tfdEmail);
        innerGrid.addRow(5,labCnpj, tfdCnpj);
        innerGrid.addRow(6,labCpf, tfdCpf);
        innerGrid.addRow(7,labRua, tfdRua);
        innerGrid.addRow(8,labNumero, tfdNumero);
        innerGrid.addRow(9,labBairro, tfdBairro);
        innerGrid.addRow(10,labCep, tfdCep);
        innerGrid.addRow(11,labCidade, tfdCidade);
        innerGrid.addRow(12,labUf, tfdUf);
        innerGrid.addRow(13,labObservacoes, tfdObservacoes);
        innerGrid.addRow(14,labTipo, tfdTipo);


        TableColumn<Object, Integer> id = new TableColumn<>("Código");
        TableColumn<Object, String> denominaçãoSocial = new TableColumn<>("Denominação Social");
        TableColumn<Object, String> nome = new TableColumn<>("Nome");
        TableColumn<Object, String> telefone = new TableColumn<>("Telefone");
        TableColumn<Object, String> email = new TableColumn<>("E-mail");
        TableColumn<Object, String> cnpj = new TableColumn<>("Cnpj");
        TableColumn<Object, String> cpf = new TableColumn<>("Cpf");
        TableColumn<Object, String> rua = new TableColumn<>("Rua");
        TableColumn<Object, Integer> numero = new TableColumn<>("Número");
        TableColumn<Object, String> bairro = new TableColumn<>("Bairro");
        TableColumn<Object, String> cep = new TableColumn<>("Cep");
        TableColumn<Object, String> cidade = new TableColumn<>("Cidade");
        TableColumn<Object, String> uf = new TableColumn<>("UF");
        TableColumn<Object, String> observacoes = new TableColumn<>("Observações");
        TableColumn<Object, Integer> tipo = new TableColumn<>("Tipo");

        tableView.getColumns().addAll(
                id,
                denominaçãoSocial,
                nome,
                telefone,
                email,
                cnpj,
                cpf,
                rua,
                numero,
                bairro,
                cep,
                cidade,
                uf,
                observacoes,
                tipo
        );

        refresh();
    }

    private int id;

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
    private JComboBox<String> comboBox = new JComboBox<String>();

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

}
