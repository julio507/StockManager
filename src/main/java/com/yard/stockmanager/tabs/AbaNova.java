package com.yard.stockmanager.tabs;
import com.yard.stockmanager.parts.MaskTextField;
import javafx.scene.control.TextField;
import javax.swing.text.MaskFormatter;
import parts.ManagementTab;

import java.text.ParseException;

public class AbaNova extends ManagementTab<Object> {


    public AbaNova(){
        super("Aba Nova");
        initComponents();
    }
    @Override
    public void refresh() {

    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void save() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void changeStatus() {

    }

    @Override
    public void select() {

    }

    @Override
    public void clear() {

    }

    private void initComponents(){

        try{
            mask.setMask("000.000.000.-00");
        }catch (ParseException e){
            System.out.println(e);
        }
        mask.setValidCharacters("0123456789");
    }

    private TextField teste = new TextField();
    private MaskFormatter mask = new MaskFormatter();
}
