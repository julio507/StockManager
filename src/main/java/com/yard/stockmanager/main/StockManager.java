/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.main;

import com.yard.stockmanager.panes.LoginPane;
import com.yard.stockmanager.panes.MainPane;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
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
        main.setStage( primaryStage );
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

    private EventHandler event = new EventHandler()
    {
        @Override
        public void handle(Event event)
        {
            if (login.doLogin())
            {
                bp.setCenter(main);
            }

            else
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Erro do Login");
                alert.setHeaderText(null);
                alert.setContentText("Verifique seu login e senha!");

                alert.showAndWait();
            }
        }
    };

    private BorderPane bp = new BorderPane();
    private MainPane main = new MainPane();
    private LoginPane login = new LoginPane(event);
    private MenuBar menu = new MenuBar();
}
