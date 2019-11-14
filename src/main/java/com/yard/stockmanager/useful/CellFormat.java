package com.yard.stockmanager.useful;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CellFormat {

    public static <T> void priceCellFormatting(TableColumn<T, BigDecimal> tableColumn) {

        tableColumn.setCellFactory(col -> new TableCell<T, BigDecimal>() {

            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("R$ %.2f", item));
                    setAlignment(Pos.CENTER);
                }

            }

        });

    }

    //para double
    public static <T> void priceDoubleCellFormatting(TableColumn<T, Double> tableColumn) {

        tableColumn.setCellFactory(col -> new TableCell<T, Double>() {

            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("R$ %.2f", item));
                    setAlignment(Pos.CENTER);
                }

            }

        });

    }

    public static <T> void dateCellFormatting(TableColumn<T, Timestamp> tableColumn, Boolean hora) {

        SimpleDateFormat format;

        if (hora) {
            format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        } else {
            format = new SimpleDateFormat("dd/MM/yyyy");
        }

        tableColumn.setCellFactory(col -> new TableCell<T, Timestamp>() {

            @Override
            protected void updateItem(Timestamp item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(format.format(item));
                    setAlignment(Pos.CENTER);
                }

            }

        });

    }

    public static <T> void phoneCellFormatting(TableColumn<T, String> tableColumn) {

        tableColumn.setCellFactory(col -> new TableCell<T, String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    String s = item;

                    StringBuilder sb = new StringBuilder();
                    sb.append("(").append(s.substring(0, 2)).append(") ").append(s.substring(2, 7))
                            .append("-").append(s.substring(7, 11));
                    setText(sb.toString());
                }

            }

        });

    }

}

