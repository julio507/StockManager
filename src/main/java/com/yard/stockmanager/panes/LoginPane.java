/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.panes;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Stack;

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
//            User u = new User();
//            u.setLogin(tfLogin.getText());
//            u.setPassword(tfPassword.getText());
//
//            UserDAO dao = new UserDAO();
//
//            return dao.doLogin(u);
            
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    private void initComponent()
    {
        setAlignment(Pos.CENTER);

        //parametros do titulo central da tela
        logoBox.setAlignment(Pos.CENTER);
        nameBox.setAlignment(Pos.CENTER);
        name.setFont(Font.font("Verdana", FontWeight.BOLD,50));
        logo.setFitWidth(200);
        logo.setFitHeight(200);

        add(base,0, 0, 2, 1);
        addRow(1, lbLogin, tfLogin);
        addRow(2, lbPassword, tfPassword);
        add(btnOk, 1, 3, 2, 2);

        btnOk.setOnAction(event);
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
    private HBox logoBox = new HBox(logo); //container do logo
    private HBox nameBox = new HBox(name); //container do nome
    private VBox base = new VBox(5, logoBox, nameBox); //container do conjunto central dos titulos

}
