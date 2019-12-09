package com.yard.stockmanager.tabs;

import com.yard.stockmanager.persistence.dao.EstoqueDAO;
import com.yard.stockmanager.persistence.dao.ReportDAO;
import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.persistence.hibernate.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuter;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockReport extends Tab {
    public StockReport(){
        super("Relatório de Estoques");
        initComponents();
    }

    public void refresh() {
        EstoqueDAO dao = new EstoqueDAO();

        List<Estoque> list = EstoqueDAO.getAll("");

        tableView.setItems(FXCollections.observableArrayList(list));
        tableView.refresh();
    }

    public void doPagination()
    {

    }
    private void initComponents(){
        tableView.setOnScroll( new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
                if( event.getDeltaY() < 0.0 )
                {
                    doPagination();
                }
            }
        } );
        reportGenerator.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try {

                    // Compila o relatorio
                    JasperReport relatorio = JasperCompileManager.compileReport("src/main/java/com/yard/stockmanager/report/entradaDeItens.jrxml");
                    // Executa relatoio
                    JasperPrint impressao = JasperFillManager.fillReport(relatorio, null, ReportDAO.getInstance().getConnection());

                    // Exibe resultado em video
                    JasperViewer.viewReport(impressao, false);


                } catch (Exception e) {
                    System.out.println("Erro ao gerar relatório: " + e);
                }
                refresh();
            }
        });


        TableColumn<Estoque, Integer> id = new TableColumn<>("ID");
        TableColumn<Estoque, String> nome = new TableColumn<>("Nome");
        TableColumn<Estoque, String> endereco = new TableColumn<>("Endereço");
        TableColumn<Estoque, String> descricao = new TableColumn<>("Descrição");
        TableColumn<Estoque, Integer> telefone = new TableColumn<>("Telefone");

        id.setCellValueFactory(new PropertyValueFactory<Estoque, Integer>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<Estoque, String>("nome"));
        endereco.setCellValueFactory(new PropertyValueFactory<Estoque, String>("endereco"));
        descricao.setCellValueFactory(new PropertyValueFactory<Estoque, String>("descricao"));
        telefone.setCellValueFactory(new PropertyValueFactory<Estoque, Integer>("telefone"));

        tableView.getColumns().addAll(
                id,
                nome,
                endereco,
                descricao,
                telefone
        );
        tablePane.setPadding(new Insets(50));
        tableView.setEditable(false);
        tablesGrid.addRow(0, tableView);
        bottomGrid.addRow(0, reportGenerator);
        tablePane.setCenter(tableView);
        tablePane.setBottom(reportGenerator);
        innerPane.setCenter(tableView);
        innerPane.setBottom(bottomGrid);

        outerGrid.addRow(0, tablePane);
        borderPane.setCenter(outerGrid);
        setContent(borderPane);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        refresh();
    }

    private Button reportGenerator = new Button("Gerar Relatório");
    protected TableView<Estoque> tableView = new TableView<Estoque>();
    private BorderPane tablePane = new BorderPane();
    private GridPane tablesGrid = new GridPane();
    private GridPane outerGrid = new GridPane();
    private BorderPane borderPane = new BorderPane();
    private BorderPane innerPane = new BorderPane();
    private GridPane bottomGrid = new GridPane();
}
