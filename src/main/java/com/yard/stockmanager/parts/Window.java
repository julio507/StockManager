package com.yard.stockmanager.parts;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Window extends Stage {

    /**
     * 
     */
    public Window( Scene owner, Parent pane, String title ) {
        Scene scene = new Scene(pane, 600, 400);
        
        getIcons().add( new Image( "img/icon.png" ) );
        initModality(Modality.WINDOW_MODAL);
        initOwner( owner.getWindow() );
        setTitle(title);
        setScene(scene);
    }

}