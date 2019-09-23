package com.yard.stockmanager.useful;

import javafx.scene.control.Alert;

public class Error {

    public static void message(String message )
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(message);
        a.setHeaderText(null);
        a.setTitle( "Aviso" );
        a.showAndWait();
    }
}
