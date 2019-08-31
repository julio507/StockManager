package com.yard.stockmanager.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;

import java.awt.*;

public class StockManagerTab extends Tab
{

    private Stage stage;
    private Font font = new Font(24);

    public StockManagerTab()
    {
        super("Gerenciamento de Estoque");
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

    private void initComponents()
    {
        //Labels
        labRua.setFont(font);
        labRua.setPrefSize(labWidth,labHeight);

        labBairro.setFont(font);
        labBairro.setPrefSize(labWidth,labHeight);

        labNumero.setFont(font);
        labNumero.setPrefSize(labWidth,labHeight);

        labCep.setFont(font);
        labCep.setPrefSize(labWidth,labHeight);

        labComplemento.setFont(font);
        labComplemento.setPrefSize(labWidth,labHeight);

        labNome.setFont(font);
        labNome.setPrefSize(labWidth,labHeight);

        labDescricao.setFont(font);
        labDescricao.setPrefSize(labWidth,labHeight);

        labTelefone.setFont(font);
        labTelefone.setPrefSize(labWidth,labHeight);


        //TaxtFields
        tfdRua.setEditable(true);
        tfdRua.setFont(font);
        tfdRua.setPrefSize(tfdWidth,tfdHeight);

        tfdBairro.setEditable(true);
        tfdBairro.setFont(font);
        tfdBairro.setPrefSize(tfdWidth,tfdHeight);

        tfdNumero.setEditable(true);
        tfdNumero.setFont(font);
        tfdNumero.setPrefSize(tfdWidth,tfdHeight);

        tfdCep.setEditable(true);
        tfdCep.setFont(font);
        tfdCep.setPrefSize(tfdWidth,tfdHeight);

        tfdComplemento.setEditable(true);
        tfdComplemento.setFont(font);
        tfdComplemento.setPrefSize(tfdWidth,tfdHeight);

        tfdNome.setEditable(true);
        tfdNome.setFont(font);
        tfdNome.setPrefSize(tfdWidth,tfdHeight);

        tfdDescricao.setEditable(true);
        tfdDescricao.setFont(font);
        tfdDescricao.setPrefSize(tfdWidth,tfdHeight);

        tfdTelefone.setEditable(true);
        tfdTelefone.setFont(font);
        tfdTelefone.setPrefSize(tfdWidth,tfdHeight);


        //Cria tabela e inclui as Labels e TextFields nas linhas e colunas
        tela.setPadding(new Insets(100));
        tela.addRow(0, labRua, tfdRua);
        tela.addRow(1, labBairro, tfdBairro);
        tela.addRow(2, labNumero, tfdNumero);
        tela.addRow(3, labComplemento, tfdComplemento);
        tela.addRow(4, labCep, tfdCep);
        tela.addRow(5, labNome, tfdNome);
        tela.addRow(6, labDescricao, tfdDescricao);
        tela.addRow(7, labTelefone, tfdTelefone);
        setContent(tela);


    }

    //Iniciação das variaveis
    private double labWidth = 180;
    private double labHeight = 90;
    private double tfdWidth = 250;
    private double tfdHeight = 30;

    //Criação dos componentes da tela
    private Label labRua = new Label("Rua:");
    private Label labBairro = new Label("Bairro:");
    private Label labNumero = new Label("Número:");
    private Label labCep = new Label("CEP:");
    private Label labComplemento = new Label("Complemento:");
    private Label labNome = new Label("Nome:");
    private Label labDescricao = new Label("Descrição:");
    private Label labTelefone = new Label("Telefone:");

    private TextField tfdRua = new TextField();
    private TextField tfdBairro = new TextField();
    private TextField tfdNumero = new TextField();
    private TextField tfdCep = new TextField();
    private TextField tfdComplemento = new TextField();
    private TextField tfdNome = new TextField();
    private TextField tfdDescricao = new TextField();
    private TextField tfdTelefone = new TextField();

    private GridPane tela = new GridPane();
}
