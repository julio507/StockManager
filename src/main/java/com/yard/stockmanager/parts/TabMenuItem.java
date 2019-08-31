/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.parts;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author julio
 */
public class TabMenuItem
        extends
        MenuItem {
    private Stage stage;

    public TabMenuItem(String text, final TabPane parent, final Tab tab) {
        super(text);

        addEventHandler(EventType.ROOT, new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!parent.getTabs().contains(tab)) {
                    parent.getTabs().add(tab);
                }

                parent.getSelectionModel().select(tab);

            }
        });
    }
}




