package com.yard.stockmanager.tabs;

import com.yard.stockmanager.persistence.dao.EstoqueDAO;
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
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import javax.print.DocFlavor;
import java.net.URL;
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
                    Session s = HibernateUtil.getSessionFactory().openSession();
                    s.beginTransaction();

                    URL arquivo = getClass().getResource("/report/vendaPassagensPeriodo.jrxml");

                    // Compila o relatorio
                    JasperReport relatorio = (JasperReport) JRLoader.loadObject(arquivo);

                    // Mapeia campos de parametros para o relatorio, mesmo que nao existam
                    Map<String, String> parametros = new HashMap<String, String>();
                    // Executa relatoio
                    //JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, s);

                    // Exibe resultado em video
                    //JasperViewer.viewReport(impressao, false);
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
        tablesGrid.addColumn(0, tableView);
        tablePane.setCenter(tableView);

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
}
