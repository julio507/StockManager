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

/**
 *
 * @author julio
 */
public abstract class TabMenuItem extends MenuItem {

    public abstract Tab getTab();

    public TabMenuItem(String text, final TabPane parent) {
        super(text);

        addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                Tab t = getTab();

                if (!parent.getTabs().contains(t)) {
                    parent.getTabs().add(t);
                }

                parent.getSelectionModel().select(t);

            }
        });
    }
}
