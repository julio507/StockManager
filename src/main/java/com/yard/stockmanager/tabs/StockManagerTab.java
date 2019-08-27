package com.yard.stockmanager.tabs;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.awt.*;

public class StockManagerTab extends Tab
{

    private Stage stage;
    private Font font = new Font(24);

    public StockManagerTab()
    {
        initComponents();
    }

    public void setStage(Stage s)
    {
        this.stage = s;
    }
    public Stage getStage()
    {
        return stage;
    }

    private void initComponents()
    {

        xlabel.setFont(font);
    }



    private Label xlabel = new Label("X");
}
