/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yard.stockmanager.parts;

import com.yard.stockmanager.main.StockManager;
import com.yard.stockmanager.persistence.dao.PermissoesDAO;
import com.yard.stockmanager.persistence.entity.Permissoes;
import com.yard.stockmanager.useful.Error;
import com.yard.stockmanager.useful.PermissionXMLReader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author julio
 */
public abstract class ManagementTab<T>
        extends
        Tab {

    private Object selected;
    private Object bottomSelected;

    private boolean printable = false;
    private boolean doubleAction = false;
    private Font font = new Font(20);
    protected int lastPage = 25;

    //variaveis de permissionamento
    private Permissoes permissoes;
    private int userId;
    private String nivel;
    private String tab;

    /**
     * Construtor padrão de tela sem permissionamento e com tabela simples.
     *
     * @param label Nome da tela
     */
    public ManagementTab(String label) {
        super(label);
        initComponents();
    }

    /**
     * Construtor de tela simples com permissionamento.
     *
     * @param label  Nome da tela
     * @param userId Id do Usuário da sessão atual
     * @param nivel  Nivel de acesso do usuário (de acordo com o banco)
     * @param tab    Nome da classe da tela
     */
    public ManagementTab(String label, int userId, char nivel, String tab) {
        super(label);
        this.userId = userId;
        if (nivel == '1') {
            this.nivel = "administrador";
        } else if (nivel == '2') {
            this.nivel = "operador";
        } else {
            this.nivel = "observador";
        }
        this.tab = tab;
        initComponents();
    }

    /**
     * Construtor para tela com tabela dupla e sem permissionamento.
     *
     * @param label        Nome da tela
     * @param doubleAction Controle de tabela dupla
     */
    public ManagementTab(String label, Boolean doubleAction) {
        super(label);
        this.doubleAction = doubleAction;
        initComponents();
    }

    /**
     * Construtor para tela com tela dupla e permissionamento por nivel.
     *
     * @param label        Nome da tela
     * @param doubleAction Controle de tabela dupla
     * @param userId       Id do Usuário da sessão atual
     * @param nivel        Nivel de acesso do usuário (de acordo com o banco)
     * @param tab          Nome da classe da tela
     */
    public ManagementTab(String label, Boolean doubleAction, int userId, char nivel, String tab) {
        super(label);
        this.doubleAction = doubleAction;
        this.userId = userId;
        if (nivel == '1') {
            this.nivel = "administrador";
        } else if (nivel == '2') {
            this.nivel = "operador";
        } else {
            this.nivel = "observador";
        }
        this.tab = tab;
        initComponents();
    }

    public abstract void refresh();

    public abstract boolean validate();

    public abstract void save();

    public abstract void edit();

    public abstract void changeStatus();

    public abstract void select();

    public abstract void clear();

    public void details() {
    }

    ;

    public void print() {
    }

    ;

    public void doPagination() {

    }

    public Object getSelected() {
        return selected;
    }

    public void setSelected(Object selected) {
        this.selected = selected;
    }

    //seleçoes da tabela inferior
    public Object getBottomSelected() {
        return bottomSelected;
    }

    public void setBottomSelected(Object bottomSelected) {
        this.bottomSelected = bottomSelected;
    }

    public String getFilter() {
        return searchField.getText();
    }

    public boolean isPrintable() {
        return printable;
    }

    public void setPrintable(boolean printable) {
        this.printable = printable;

        printButton.setDisable(!printable);
    }

    //metodos de edição das tabelas
    public void selectBottom() {
    }//seleção dos registros da tabela inferior

    public void editUpperRegister() {
    }

    public void removeUpperRegister() {
    }

    public void editBottomRegister() {
    }

    public void removeBottomRegister() {
    }

    //metodos para desabilitar os controles de edição das tabelas
    public void disableUpperButtons() {
        editButton.setDisable(true);
        removeButton.setDisable(true);
    }

    public void disableBottomButtons() {
        editBottomButton.setDisable(true);
        removeBottomButton.setDisable(true);
    }

    //metodos para habilitar os controles de edição das tabelas
    public void enableUpperButtons() {
        editButton.setDisable(false);
        removeButton.setDisable(false);
    }

    public void enableBottomButtons() {
        editBottomButton.setDisable(false);
        removeBottomButton.setDisable(false);
    }

    //verifica permissoes
    private boolean hasPermission(String regra) {
        if ((permissoes = PermissoesDAO.hasPermission(userId, tab, regra)) != null) {
            if (permissoes.isAtivo() == '1') {
                return true;
            } else if (permissoes.isAtivo() == '0') {
                return false;
            }
        } else {
            PermissionXMLReader reader = new PermissionXMLReader(nivel, regra, tab);
            reader.fazerParsing("permissoes.xml");
            return reader.hasAccess();
        }
        return false;
    }

    private void initComponents() {

        mark.setTextFill(new Color(1, 0, 0, 0));

        ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setPercentWidth(30);

        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setPercentWidth(70);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);

        searchGrid.addColumn(0, searchField);
        searchGrid.addColumn(1, searchButton);

        searchField.setPrefWidth(925);
        searchField.setFont(font);
        searchButton.setFont(font);

        searchGrid.setPrefWidth(Double.MAX_VALUE);

        outerGrid.getColumnConstraints().addAll(cons1, cons2);
        outerGrid.getRowConstraints().add(row1);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setEditable(false);
        outerGrid.setPadding(new Insets(10));

        tablePane.setTop(searchGrid);

        //duas tabelas
        if (!doubleAction) {
            tablePane.setCenter(tableView);
        } else {
            upperTableButtonsGrid.addRow(0, editButton, removeButton);
            bottomTableButtonsGrid.addRow(0, editBottomButton, removeBottomButton);

            tableViewBottom.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tableViewBottom.setEditable(false);
            tablesGrid.addColumn(0, tableView, upperTableButtonsGrid, tableViewBottom, bottomTableButtonsGrid);
            tablePane.setCenter(tablesGrid);
        }

        tablePane.setPadding(new Insets(10));
        innerGrid.setPadding(new Insets(10));

        outerGrid.addColumn(0, innerPane);
        outerGrid.addColumn(1, tablePane);

        innerPane.setCenter(innerGrid);
        innerPane.setBottom(bottomGrid);

        bottomGrid.add(need, 0, 0, 3, 1);
        bottomGrid.addRow(1, saveButton, newButton, disableButton, printButton);

        bottomGrid.setAlignment(Pos.CENTER);

        borderPane.setCenter(outerGrid);

        setContent(borderPane);

        printButton.setDisable(!printable);

        tableView.setOnScroll(new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() < 0.0) {
                    doPagination();
                }
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (hasPermission("inserir") || userId == 0) {
                    if (validate()) {
                        if (getSelected() == null) {
                            save();
                            clear();
                        } else {
                            edit();
                        }

                        refresh();
                    }
                } else {
                    Error.message("Voce não tem permissão para realizar esta ação.\nPara solicitar acesso entre em contato com o suporte");
                }
            }
        });

        disableButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (hasPermission("modificar") || userId == 0) {
                    if (selected != null) {
                        changeStatus();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Erro ao trocar o status");
                        alert.setHeaderText(null);
                        alert.setContentText("Nenhum item selecionado");

                        alert.showAndWait();
                    }
                } else {
                    Error.message("Voce não tem permissão para realizar esta ação.\nPara solicitar acesso entre em contato com o suporte");
                }
            }
        });

        newButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clear();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setSelected(newValue);

                select();
            }
        });

        //selecão da tabela inferior
        tableViewBottom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                setBottomSelected(newValue);

                selectBottom();
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refresh();
            }
        });

        printButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                print();
            }
        });

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (hasPermission("modificar") || userId == 0) {
                    editUpperRegister();
                } else {
                    Error.message("Voce não tem permissão para realizar esta ação.\nPara solicitar acesso entre em contato com o suporte");
                }
            }
        });

        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (hasPermission("remover") || userId == 0) {
                    removeUpperRegister();
                } else {
                    Error.message("Voce não tem permissão para realizar esta ação.\nPara solicitar acesso entre em contato com o suporte");
                }
            }
        });

        editBottomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (hasPermission("modificar") || userId == 0) {
                    editBottomRegister();
                } else {
                    Error.message("Voce não tem permissão para realizar esta ação.\nPara solicitar acesso entre em contato com o suporte");
                }
            }
        });

        removeBottomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (hasPermission("remover") || userId == 0) {
                    removeBottomRegister();
                } else {
                    Error.message("Voce não tem permissão para realizar esta ação.\nPara solicitar acesso entre em contato com o suporte");
                }
            }
        });
    }

    protected Label mark = new Label("*");
    private Label need = new Label("* - Campo Obrigatório");

    private TextField searchField = new TextField();
    private Button searchButton = new Button("Procurar");
    private GridPane searchGrid = new GridPane();

    private Button saveButton = new Button("Salvar");
    private Button newButton = new Button("Limpar/Novo");
    private Button disableButton = new Button("Desabilitar/Habilitar");
    private Button printButton = new Button("Imprimir");

    private Button editButton = new Button("Editar");
    private Button removeButton = new Button("Remover");
    private Button editBottomButton = new Button("Editar");
    private Button removeBottomButton = new Button("Remover");

    protected GridPane innerGrid = new GridPane();
    protected TableView<T> tableView = new TableView<T>();
    protected TableView<T> tableViewBottom = new TableView<T>();

    private GridPane outerGrid = new GridPane();
    private GridPane bottomGrid = new GridPane();
    private GridPane tablesGrid = new GridPane();
    private GridPane upperTableButtonsGrid = new GridPane();
    private GridPane bottomTableButtonsGrid = new GridPane();
    private BorderPane tablePane = new BorderPane();
    private BorderPane borderPane = new BorderPane();
    private BorderPane innerPane = new BorderPane();
}
