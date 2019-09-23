package com.yard.stockmanager.parts;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.hibernate.cfg.NotYetImplementedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class Utilities{

    //constroi os campos de texto na tela
    public static void tfdBuilder(GridPane grid, List<Control> fields, int col)
    {
        for (int i = 0; i < fields.size(); i++){
            grid.add(fields.get(i), col, i);
        }
    }

    //constroi os campos de texto na tela a partir de uma linha especifica
    public static void tfdBuilder(GridPane grid, List<Control> fields, int col, int startAt)
    {
        for (int i = startAt; i < (fields.size()+startAt); i++){
            grid.add(fields.get(i-startAt), col, i);
        }
    }

    //constroi os labels na tela
    public static void labBuilder(GridPane grid, List<Label> fields, int col)
    {
        for (int i = 0; i < fields.size(); i++){
            grid.add(fields.get(i), col, i);
        }
    }

    //constroi os labels na tela a partir de uma linha especifica
    public static void labBuilder(GridPane grid, List<Label> fields, int col, int startAt)
    {
        for (int i = startAt; i < (fields.size()+startAt); i++){
            grid.add(fields.get(i-startAt), col, i);
        }
    }

    //constroi um formulario padrao (label, field)
    public static void formBuilder(GridPane grid, List<Control> fields, List<Label> labels, int col, int startAt)
    {
        for (int i = startAt; i < (fields.size()+startAt); i++){
            grid.add(labels.get(i-startAt), col, i);
            grid.add(fields.get(i-startAt), col+1, i);
        }
    }

    //limpa os campos da tela em um intervalo de linhas
    public static void clear (GridPane grid, int col, int startAt, int endAt, boolean focusOnFirst){
        throw null;
    }

}
