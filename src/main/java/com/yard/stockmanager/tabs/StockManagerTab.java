package com.yard.stockmanager.tabs;

import com.yard.stockmanager.parts.ManagementTab;
import com.yard.stockmanager.persistence.dao.EstoqueDAO;
import com.yard.stockmanager.persistence.entity.Endereco;
import com.yard.stockmanager.persistence.entity.Estoque;
import com.yard.stockmanager.useful.Error;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class StockManagerTab extends ManagementTab<Estoque>
{

    private Estoque selected;

    public StockManagerTab()
    {
        super("Gerenciamento de Estoque");
        initComponents();
    }

    @Override
    public void refresh() {
        List<Estoque> list = EstoqueDAO.getAll( getFilter() );

        tableView.setItems(FXCollections.observableArrayList( list ));
        tableView.refresh();
    }

    @Override
    public boolean validate() {

        String errors = "";

        if (tfdNome.getText().isEmpty())
        {
            errors=errors+"Nome";
        }

        if (tfdDescricao.getText().isEmpty())
        {
            errors= errors+"Descrição";
        }
        if(tfdTelefone.getText().isEmpty())
        {
            errors=errors+"Telefone";
        }
        if(tfdEndereco.getText().isEmpty())
        {
            errors=errors+"Endereço";
        }

        if (!errors.isEmpty())
        {
            Error.messageAndLog(errors);
        }

        return errors.isEmpty();
    }

    @Override
    public void save() {
        Estoque est = new Estoque();
        Endereco end = new Endereco();

        est.setNome(tfdNome.getText());
        est.setDescricao(tfdDescricao.getText());
        est.setTelefone(tfdTelefone.getText());
        end.setId(1);
        est.setEndereco(end);

        EstoqueDAO estDao = new EstoqueDAO();
        estDao.add(est);

        refresh();
    }

    @Override
    public void edit() {
        Estoque est = new Estoque();
        Endereco end = new Endereco();

        est.setId(this.id);
        est.setNome(tfdNome.getText());
        est.setDescricao(tfdDescricao.getText());
        est.setTelefone(tfdTelefone.getText());
        end.setId(1);
        est.setEndereco(end);

        EstoqueDAO estDao = new EstoqueDAO();
        estDao.update(est);
        System.out.println("Edita");

        refresh();
    }

    @Override
    public void changeStatus() {

    }

    @Override
    public void select() {
        selected = (Estoque) getSelected();

        if (selected != null)
        {
            tfdNome.setText(selected.getNome());
            tfdDescricao.setText(selected.getDescricao());
            tfdTelefone.setText(selected.getTelefone());
            id = selected.getId();
        }

        else
        {
            clear();
        }
    }

    @Override
    public void clear() {

    }


    private void initComponents()
    {

        innerGrid.addRow(0,labNome, tfdNome);
        innerGrid.addRow(1,labEndeeco, tfdEndereco);
        innerGrid.addRow(2,labDescricao, tfdDescricao);
        innerGrid.addRow(3,labTelefone, tfdTelefone);


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

        refresh();
        
    }

    private int id;

    //Criação dos componentes da tela
    private Label labEndeeco = new Label("Rua:");
    private Label labNome = new Label("Nome:");
    private Label labDescricao = new Label("Descrição:");
    private Label labTelefone = new Label("Telefone:");

    private TextField tfdEndereco = new TextField();
    private TextField tfdNome = new TextField();
    private TextField tfdDescricao = new TextField();
    private TextField tfdTelefone = new TextField();

}
