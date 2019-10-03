/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.panes;

import com.yard.stockmanager.persistence.dao.FuncionarioDAO;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author julio
 */
public class LoginPane extends GridPane
{

    public LoginPane(EventHandler event)
    {
        this.event = event;
        initComponent();
    }

    public boolean doLogin()
    {
        try
        {
            FuncionarioDAO dao = new FuncionarioDAO();
            return dao.doLogin( tfLogin.getText(), tfPassword.getText() );
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private void initComponent()
    {
        setAlignment(Pos.CENTER);
        setBackground(background);

        //parametros do titulo central da tela
        //logo
        hLogoBox.setAlignment(Pos.CENTER);
        logo.setFitWidth(200);
        logo.setFitHeight(200);
        //nome da empresa
        hNameBox.setAlignment(Pos.CENTER);
        name.setFont(Font.font("Verdana", FontWeight.BOLD,50));
        //desenvolvedora
        hBottomBox.setAlignment(Pos.CENTER);
        bottomText.setFont(Font.font("Verdana", FontWeight.LIGHT, FontPosture.ITALIC,10));

        //componentes na tela
        add(vBaseBox,0, 0, 2, 1);
        addRow(1, lbLogin, tfLogin);
        addRow(2, lbPassword, tfPassword);
        add(btnOk, 1, 3, 2, 2);
        add(hBottomBox, 0,5,2,3);

        //acao login
        btnOk.setOnAction(event);


        //acao para setar foco em outro campo com enter
        tfLogin.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    tfPassword.requestFocus();
                }
            }
        });


        tfPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    btnOk.requestFocus();
                }
            }
        });


        btnOk.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    doEvent();
                }
            }

        });
    }

    private void doEvent(){
        btnOk.setOnAction(event);
        btnOk.fire();
    }


    private EventHandler event;

    private TextField tfLogin = new TextField();
    private TextField tfPassword = new PasswordField();
    private Label lbLogin = new Label("Login ");
    private Label lbPassword = new Label("Senha ");
    private Button btnOk = new Button("Login");

    //Bloco do titulo central da tela de login
    private ImageView logo = new ImageView(new Image("img/logo.png")); //logotipo
    private Text name = new Text("StockManager"); //nome do software
    private HBox hLogoBox = new HBox(logo); //container do logo
    private HBox hNameBox = new HBox(name); //container do nome
    private VBox vBaseBox = new VBox(5, hLogoBox, hNameBox); //container do conjunto central dos titulos

    //Propriedades do backgroud da tela de login
    private BackgroundSize backSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
    private Background background = new Background(new BackgroundImage(new Image("img/background.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backSize));

    //Rodape da Tela
    private Text bottomText = new Text("2019 - SoftwareYard. Inc "); //Mensagem de rodape
    private HBox hBottomBox = new HBox(bottomText); //container da mensagem de rodape

}
