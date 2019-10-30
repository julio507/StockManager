package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.dao.UserRegisterDAO;
import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.entity.Funcionario;
import com.yard.stockmanager.useful.Error;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class UserRegisterTab extends ManagementTab<Object> {

    private Stage stage;
    private Font font = new Font(14);

    public UserRegisterTab() {
        super("Cadastro de Usuario");
        initComponents();
    }


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

        if (tfdCodigo.getText().isEmpty()) {
            errors = errors + "Código";
        }
        if (tfdNomeUsuario.getText().isEmpty()) {
            errors = errors + "Nome do Usuário";
        }
        if (tfdSenha.getText().isEmpty()) {
            errors = errors + "Senha";
        }
        if (tfdNomeFuncionario.getText().isEmpty()) {
            errors = errors + "Nome do Funcionário";
        }
        if (tfdSenha.getText().isEmpty()) {
            errors = errors + "Senha";
        }
        if (tfdEmail.getText().isEmpty()) {
            errors = errors + "Email";
        }
        if (tfdTelefone.getText().isEmpty()) {
            errors = errors + "Telefone";
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
        innerGrid.addRow(0, labCodigo, tfdCodigo);
        innerGrid.addRow(1, labNomeUsuario, tfdNomeUsuario);
        innerGrid.addRow(2, labSenha, tfdSenha);
        innerGrid.addRow(3, labNomeFuncionario, tfdNomeFuncionario);
        innerGrid.addRow(4, labEmail, tfdEmail);
        innerGrid.addRow(5, labTelefone, tfdTelefone);
        innerGrid.addRow(6, labFuncao, tfdFuncao);
        innerGrid.addRow(7, labNivelAcesso, tfdNivelAcesso);

        TableColumn<Object, Integer> id = new TableColumn<>("Código");
        TableColumn<Object, String> nomeUsuario = new TableColumn<>("Nome Usuário");
        TableColumn<Object, String> Senha = new TableColumn<>("Senha");
        TableColumn<Object, String> nomeFuncionario = new TableColumn<>("Nome do Funcionário");
        TableColumn<Object, String> Email = new TableColumn<>("Email");
        TableColumn<Object, String> Telefone = new TableColumn<>("Telefone");
        TableColumn<Object, String> Funcao = new TableColumn<>("Função");
        TableColumn<Object, Integer> nivelAcesso = new TableColumn<>("Nível de Acesso");

        tableView.getColumns().addAll(
                id,
                nomeUsuario,
                Senha,
                nomeFuncionario,
                Email,
                Telefone,
                Funcao,
                nivelAcesso
        );

        refresh();
    }

    //Criação dos componentes da tela
    private Label labCodigo = new Label("Código:");
    private Label labNomeUsuario = new Label("Login:");
    private Label labSenha = new Label("Senha:");
    private Label labNomeFuncionario = new Label("Nome do Funcionário");
    private Label labEmail = new Label("E-mail:");
    private Label labTelefone = new Label("Telefone:");
    private Label labFuncao = new Label("Função:");
    private Label labNivelAcesso = new Label("Nível de Acesso:");

    private TextField tfdCodigo = new TextField();
    private TextField tfdNomeUsuario = new TextField();
    private TextField tfdSenha = new TextField();
    private TextField tfdNomeFuncionario = new TextField();
    private TextField tfdEmail = new TextField();
    private TextField tfdTelefone = new TextField();
    private TextField tfdFuncao = new TextField();
    private TextField tfdNivelAcesso = new TextField();
}
