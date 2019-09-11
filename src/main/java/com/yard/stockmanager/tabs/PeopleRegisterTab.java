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

public class PeopleRegisterTab extends parts.ManagementTab<Object>
{

    private Stage stage;
    private Font font = new Font(14);

    public PeopleRegisterTab()
    {
        super("Cadastro de Pessoas");
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
