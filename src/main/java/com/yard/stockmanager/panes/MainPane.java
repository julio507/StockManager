/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.panes;

import com.yard.stockmanager.parts.TabMenuItem;
import com.yard.stockmanager.tabs.AbaNova;
import com.yard.stockmanager.tabs.PeopleRegisterTab;
import com.yard.stockmanager.tabs.StockManagerTab;
import com.yard.stockmanager.tabs.UserRegisterTab;
import com.yard.stockmanager.tabs.CategorManagerTab;
import com.yard.stockmanager.tabs.AbaNova;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author julio
 */
public class MainPane
        extends
        BorderPane
{

    private Stage stage;
    
    public MainPane()
    {
        managementMenu.getItems().addAll(
//                flavorItem,
//                sizeItem,
//                districtItem,
//                otherItem
                stockManagerTab,
                userRegisterTab,
                categorManagerTab,
                abaNova,
                peopleRegisterTab
        );
//
//        operationsMenu.getItems().addAll(
//                sellItem
//        );
//
//        queryMenu.getItems().addAll(
//                sellQueryItem,
//                userQueryItem
//        );
        
        menu.getMenus().addAll(
                managementMenu,
                operationsMenu,
                queryMenu
        );

        setTop(menu);
        setRight(vBaseBox);
        vBaseBox.setAlignment(Pos.BOTTOM_RIGHT);
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        hLogoBox.setAlignment(Pos.CENTER);
        name.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        hNameBox.setAlignment(Pos.CENTER);
        setBackground(background);
        setCenter(tabPane);

    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
        //stockManagerTab.setStage(stage);
//        st.setStage(stage);
    }

    private TabPane tabPane = new TabPane();
    //private StockManagerTab stockManagerTab = new StockManagerTab();

    private Menu managementMenu = new Menu("Gerenciamento");
    private Menu operationsMenu = new Menu("Operações");
    private Menu queryMenu = new Menu("Consultas");

    private TabMenuItem stockManagerTab = new TabMenuItem("Gerenciamento Estoque", tabPane, new StockManagerTab());
    private TabMenuItem userRegisterTab = new TabMenuItem ("Cadastro Usuário", tabPane, new UserRegisterTab());
    private TabMenuItem categorManagerTab = new TabMenuItem("Cadastro de Categoria", tabPane, new CategorManagerTab());
    private TabMenuItem abaNova = new TabMenuItem("aba Nova", tabPane, new AbaNova());
    private TabMenuItem peopleRegisterTab = new TabMenuItem("Cadastro de Pessoa",tabPane, new PeopleRegisterTab());
//    private SellTab st = new SellTab();
//    
//    private TabMenuItem flavorItem = new TabMenuItem("Sabores", tabPane, new FlavorTab());
//    private TabMenuItem sizeItem = new TabMenuItem("Tamanhos", tabPane, new SliceTab());
//    private TabMenuItem districtItem = new TabMenuItem("Bairros", tabPane, new DistrictTab());
//    private TabMenuItem otherItem = new TabMenuItem("Outros Itens", tabPane, new ItemTab());
//    private TabMenuItem userItem = new TabMenuItem("Usuarios", tabPane, new UserTab());

//    private TabMenuItem sellItem = new TabMenuItem("Vendas", tabPane, st );
//
//    private TabMenuItem sellQueryItem = new TabMenuItem("Vendas por período", tabPane, new SellQueryTab() );
//    
//    private TabMenuItem userQueryItem = new TabMenuItem("Vendas por usuário", tabPane, new UserQueryTab() );
    
    private MenuBar menu = new MenuBar();

    //Bloco do titulo central da tela de login
    private ImageView logo = new ImageView(new Image("img/logo.png")); //logotipo
    private Text name = new Text("StockManager"); //nome do software
    private HBox hLogoBox = new HBox(logo); //container do logo
    private HBox hNameBox = new HBox(name); //container do nome
    private VBox vBaseBox = new VBox(5, hLogoBox, hNameBox); //container do conjunto central dos titulos

    //Propriedades do backgroud da tela Principal
    private BackgroundSize backSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
    private Background background = new Background(new BackgroundImage(new Image("img/background.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backSize));

}
