/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.main;

import com.yard.stockmanager.panes.LoginPane;
import com.yard.stockmanager.panes.MainPane;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import com.yard.stockmanager.useful.Error;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author julio.rosa
 */
public class StockManager extends Application
{

    @Override
    public void start(Stage primaryStage)
    {
        HibernateUtil.buildSessionFactory();

        StackPane root = new StackPane();

        bp.setCenter(login);

        root.getChildren().add(bp);

        Scene scene = new Scene(root, 720, 460);

        primaryStage.setTitle("StockManager");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    private EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if (login.doLogin())
            {
                bp.setCenter(main);
            }

            else
            {
                Error.message( "Verifique seu Login e Senha" );
                
                Error.log( "Falha de Login", this.getClass() );
            }
        }
    };

    private BorderPane bp = new BorderPane();
    private MainPane main = new MainPane();
    private LoginPane login = new LoginPane(event);
}
