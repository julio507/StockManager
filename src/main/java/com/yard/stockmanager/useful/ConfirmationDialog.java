package com.yard.stockmanager.useful;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ConfirmationDialog {

    public static char confirm(String aviso, String mensagem){
        char t = ' ';

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(aviso);
        alert.setContentText(mensagem);

        ButtonType yes = new ButtonType("Sim");
        ButtonType no = new ButtonType("Não");
        ButtonType cancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yes, no);
        alert.getButtonTypes().add(cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes){
            t = 'y';
        }else if (result.get() == no){
            t = 'n';
        }else{
            t = 'c';
        }

        return t;
    }
}
