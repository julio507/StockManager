/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.panes;

import com.yard.stockmanager.parts.TabMenuItem;
import com.yard.stockmanager.tabs.*;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author julio
 */
public class MainPane
        extends
        BorderPane
{

    public MainPane()
    {
        managementMenu.getItems().addAll(
                stockManagerTab,
                userRegisterTab,
                categoryManagerTab,
                peopleRegisterTab,
                departmentRegisterTab,
                itensStockTab,
                addressManagerTab,
                cityRegistreTab,
                productRegisterTab,
                ItensManagerTab
        );
        
        operationsMenu.getItems().addAll(
            calendarTab
        );

        menu.getMenus().addAll(
                managementMenu,
                operationsMenu,
                queryMenu
        );

        setTop(menu);

        //container logo e nome
        vBaseBox.setAlignment(Pos.BOTTOM_RIGHT);
        //preferencias da logo
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        hLogoBox.setAlignment(Pos.CENTER);
        //preferencias da marca
        name.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        hNameBox.setAlignment(Pos.CENTER);
        //background
//        setBackground(background);
        //stackpane central
        back.getChildren().add(vBaseBox);
        back.getChildren().add(tabPane);
        //parte central borderPane
        setCenter(back);

    }

    private TabPane tabPane = new TabPane();
    private StackPane back = new StackPane();
    //private StockManagerTab stockManagerTab = new StockManagerTab();

    private Menu managementMenu = new Menu("Gerenciamento");
    private Menu operationsMenu = new Menu("Operações");
    private Menu queryMenu = new Menu("Consultas");

    private TabMenuItem stockManagerTab = new TabMenuItem("Gerenciamento Estoque", tabPane)
    {
        @Override
        public Tab getTab()
        {
            return new StockManagerTab();
        }
    };

    private TabMenuItem userRegisterTab = new TabMenuItem ("Cadastro Usuário", tabPane)
    {
        @Override
        public Tab getTab()
        {
            return new UserRegisterTab();
        }
    };

    private TabMenuItem categoryManagerTab = new TabMenuItem("Cadastro de Categoria", tabPane )
    {
       @Override
       public Tab getTab() {
        return new CategorManagerTab();
        } 
    };
    
    private TabMenuItem peopleRegisterTab = new TabMenuItem("Cadastro de Pessoa",tabPane)
    {
        @Override
        public Tab getTab() {
            return new PeopleRegisterTab();
        }
    }; 

    private TabMenuItem departmentRegisterTab = new TabMenuItem("Cadastro de Departamentos",tabPane)
    {
        @Override
        public Tab getTab() {
            return new DepartmentRegisterTab();
        }
    };

    private TabMenuItem ItensManagerTab = new TabMenuItem("Saída de Produtos",tabPane)
    {
        @Override
        public Tab getTab() {
            return new ItensManagerTab();
        }
    };



    
    private TabMenuItem itensStockTab = new TabMenuItem("Cadastrar Itens em Estoque",tabPane)
    {
        @Override
        public Tab getTab() {
            return new ItensStockTab();
        }
    };

    private TabMenuItem calendarTab = new TabMenuItem( "Calendario", tabPane )
    {
        @Override
        public Tab getTab() {
            return new CalendarManagementTab();
        }
    };

    private TabMenuItem addressManagerTab = new TabMenuItem("Gerenciamento de Endereços", tabPane )
    {
        @Override
        public Tab getTab() { return new AddressManagerTab(); }
    };

    private TabMenuItem cityRegistreTab = new TabMenuItem("Cadastro de Cidades", tabPane ){
        @Override
        public Tab getTab() { return new CityRegisterTab(); }
    };

    private TabMenuItem productRegisterTab = new TabMenuItem("Cadastro de Produtos", tabPane ){
        @Override
        public Tab getTab() { return new ProductRegisterTab(); }
    };


    private MenuBar menu = new MenuBar();

    //Bloco do titulo central da tela de login
    private ImageView logo = new ImageView(new Image("img/logo.png")); //logotipo
    private Text name = new Text("StockManager"); //nome do software
    private HBox hLogoBox = new HBox(logo); //container do logo
    private HBox hNameBox = new HBox(name); //container do nome
    private VBox vBaseBox = new VBox(5, hLogoBox, hNameBox); //container do conjunto central dos titulos

//    //Propriedades do backgroud da tela Principal
//    private BackgroundSize backSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
//    private Background background = new Background(new BackgroundImage(new Image("img/background.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backSize));

}
