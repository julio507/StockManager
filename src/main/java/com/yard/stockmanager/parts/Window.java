package com.yard.stockmanager.parts;

import com.yard.stockmanager.useful.Error;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Window extends Stage {

    private Object selected;

    /**
     * Cria uma tela modal que exibe qualquer pane que seja passado como parâmetro
     *
     * @param owner - Tela onde a janela será chamada
     * @param pane - pane a ser exibido
     * @param title - Titulo que sera exibido na Janela
     */
    public Window(Scene owner, Parent pane, String title ) {
        Scene scene = new Scene(pane, 600, 400);
        
        getIcons().add( new Image( "img/icon.png" ) );
        initModality(Modality.WINDOW_MODAL);
        initOwner( owner.getWindow() );
        setTitle(title);
        setScene(scene);
    }

    /**
     * Cria uma tela de seleção que traz itens de outras telas de cadastro que extendem a classe ManagementTab()
     *
     * @param owner - Tela onde a janela de seleção será chamada
     * @param tab - Tela que possui os itens a serem selecioandos
     * @param title - Titulo que sera exibido na Janela de seleção
     */
    public Window(Scene owner, ManagementTab<?> tab, String title) {
        GridPane controlGrid = new GridPane();//grid de controles
        BorderPane basePane = new BorderPane();//fundo
        TabPane tabPane = new TabPane();//tab
        tab.setClosable(false);

        //botoes
        //botão selecionar
        Button selecionar = new Button("Selecionar");
        selecionar.setPrefSize(100,10);
        selecionar.setOnAction(Event ->
        {
            if (!tab.tableView.getSelectionModel().isEmpty()){
                selected = tab.tableView.getSelectionModel().getSelectedItem();
                ((Stage)(selecionar.getScene().getWindow())).close();
            }else{
                Error.message("Nenhum item selecionado!");
            }
        });

        //botão fechar
        Button fechar = new Button("Fechar");
        fechar.setPrefSize(100,10);
        fechar.setOnAction(Event -> {
            ((Stage)(fechar.getScene().getWindow())).close();
        });

        //set dos controles
        controlGrid.addRow(1, selecionar, fechar);
        controlGrid.setAlignment(Pos.CENTER_RIGHT);
        controlGrid.setPadding(new Insets(10));

        //Adição da tab na tela
        tabPane.getTabs().add(tab);

        //set do tab e controles
        basePane.setCenter(tabPane);
        basePane.setBottom(controlGrid);


        //inicailização da Scene
        Scene scene = new Scene(basePane,(int) Math.ceil(owner.getWidth()/1.25), (int) Math.ceil((owner.getHeight()/1.25)+20));

        //definições da Scene
        getIcons().add( new Image( "img/icon.png" ) );
        initModality(Modality.WINDOW_MODAL);
        initOwner( owner.getWindow() );
        setTitle(title);
        setScene(scene);
    }

    /**
     * Método que retorna o item selecionado na janela de seleção(Funciona somente com telas que implementem a Classe ManagementTab())
     *
     * @return - Objeto selecionado na janela de seleção
     */
    public Object getSelected() {
        return selected;
    }

}