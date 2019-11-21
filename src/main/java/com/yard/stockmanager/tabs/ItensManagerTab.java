package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.entity.Produto;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;

public class ItensManagerTab extends ManagementTab<Produto>
{

        public ItensManagerTab()
        {

            super("Saída de Produtos");
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


        private void initComponents()
        {

            innerGrid.addRow(0,labNumeroNf, tfdNumeroNf);
            innerGrid.addRow(1,labData, tfdData);
            innerGrid.addRow(2,labQuantidade, tfdQuant);
            innerGrid.addRow(3,labDepartamento, tfdDepartamento);
            innerGrid.addRow(4,labCategoria, tfdCategoria);
            innerGrid.addRow(5,labValorItem, tfdValorItem);
            innerGrid.addRow(6,labValorTotal, tfdValorTotal);


            TableColumn<Produto, Integer> numeronf = new TableColumn<>("Número NF");
            TableColumn<Produto, Date> data = new TableColumn<>("Data");
            TableColumn<Produto, Integer> quantidade = new TableColumn<>("Quantidade");
            TableColumn<Produto, Integer> departamento = new TableColumn<>("Departamento");
            TableColumn<Produto, Integer> categoria = new TableColumn<>("Categoria");
            TableColumn<Produto, Double> valoritem = new TableColumn<>("Valor Unitário");
            TableColumn<Produto, Double> valortotal = new TableColumn<>("Valor Total");

            numeronf.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("numeronf"));
            data.setCellValueFactory(new PropertyValueFactory<Produto, Date>("data"));
            quantidade.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("quantidade"));
            departamento.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("departamento"));
            categoria.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("categoria"));
            valoritem.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valoritem"));
            valortotal.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valortotal"));


            tableView.getColumns().addAll(
                    numeronf,
                    data,
                    quantidade,
                    departamento,
                    categoria,
                    valoritem,
                    valortotal
            );

            refresh();

        }


        //Criação dos componentes da tela
        private Label labNumeroNf = new Label("Número NF:");
        private Label labData = new Label("Data:");
        private Label labQuantidade = new Label("Quantidade:");
        private Label labDepartamento = new Label("Departamento:");
        private Label labCategoria = new Label("Categoria:");
        private Label labValorItem = new Label("Valor Unitário:");
        private Label labValorTotal = new Label("Valor Total:");

        private TextField tfdNumeroNf = new TextField();
        private TextField tfdData = new TextField();
        private TextField tfdQuant = new TextField();
        private TextField tfdDepartamento = new TextField();
        private TextField tfdCategoria = new TextField();
        private TextField tfdValorItem = new TextField();
        private TextField tfdValorTotal = new TextField();

    }

