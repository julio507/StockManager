/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.panes;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author julio
 */
public class LoginPane
        extends
        GridPane
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

        btnOk.setText("Login");

        addRow(0, lbLogin, tfLogin);
        addRow(1, lbPassword, tfPassword);
        add(btnOk, 1, 2, 2, 2);

        btnOk.setOnAction(event);
    }

    private EventHandler event;

    private TextField tfLogin = new TextField();
    private TextField tfPassword = new PasswordField();
    private Label lbLogin = new Label("Login");
    private Label lbPassword = new Label("Senha");
    private Button btnOk = new Button();
}
