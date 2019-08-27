/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.panes;

import com.yard.stockmanager.parts.TabMenuItem;
import com.yard.stockmanager.tabs.StockManagerTab;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
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
                stockManagerTab
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
}
