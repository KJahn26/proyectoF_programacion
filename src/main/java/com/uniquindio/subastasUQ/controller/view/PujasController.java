package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.NumberStringConverter;

public class PujasController {

     AnuncioController anuncioController;
     ObservableList<ProductoDto> listaProductos = FXCollections.observableArrayList();
    ObservableList<ProductoDto> listaProductosPuja = FXCollections.observableArrayList();
    ProductoDto seleccionar;
    int seleccionarPosicion=0;

    @FXML
    private AnchorPane AnchorPrincipal;

    @FXML
    private AnchorPane AnchorProductos;

    @FXML
    private TableView<ProductoDto> tableProductos;
    @FXML
    private TableView<ProductoDto> tablePujas;

    @FXML
    private TableColumn<ProductoDto, String> columnNumeralAnuncio;

    @FXML
    private TableColumn<ProductoDto, String> columnNombreAnuncio;

    @FXML
    private TableColumn<ProductoDto, String> columnTipoProductoAnuncio;

    @FXML
    private TableColumn<ProductoDto, String> columnDescripcionAnuncio;

    @FXML
    private TableColumn<ProductoDto, String> columnNameAnuncianteAnuncio;

    @FXML
    private TableColumn<ProductoDto, String> columnFechaFinalAnuncio;

    @FXML
    private TableColumn<ProductoDto, String> columnValorProductoAnuncio;

    @FXML
    private AnchorPane AnchorPuja;

    @FXML
    private TableColumn<ProductoDto, String> columnNumeralPuja;

    @FXML
    private TableColumn<ProductoDto, String> columnTipoProductoPuja;

    @FXML
    private TableColumn<ProductoDto, String> columnDescripcionPuja;

    @FXML
    private TableColumn<ProductoDto, String> columnNombreAnuncinatePuja;

    @FXML
    private TableColumn<ProductoDto, String> columnFechaInicioPuja;

    @FXML
    private TableColumn<ProductoDto, String> columnFechaFInalPuja;

    @FXML
    private TableColumn<ProductoDto, String> columValorProductoPuja;

    @FXML
    private Button btnPujas;

    @FXML
    private Button btnEliminarPuja;

    @FXML
    private TextField txtxValorPuja;
    public void initialize() {
        anuncioController = new AnuncioController();
        initDataBindingProductos();
        obtenerProductos();
        tableProductos.getItems().clear();
        tableProductos.setItems(listaProductos);
        // Configura el TextFormatter para permitir solo números
        NumberStringConverter converter = new NumberStringConverter();
        TextFormatter<Number> textFormatter = new TextFormatter<>(converter, 0, change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                return null;
            }
            return change;
        });

        txtxValorPuja.setTextFormatter(textFormatter);
    }

    @FXML
    void ActionPujar(ActionEvent event) {
        initDataBindingPujas();
        listenerSelection();
        if (seleccionar!=null)
        {
            listaProductosPuja.addAll(seleccionar);
            
            tablePujas.setItems(listaProductosPuja);
        }
        else
        {
            System.out.println("NO ah seleccioando nada");
        }




    }

    @FXML
    void actionEliminarPuja(ActionEvent event) {

    }

    public void obtenerProductos ()
    {
        listaProductos.addAll(anuncioController.obtenerProducto());
    }
    private void listenerSelection() {
        tableProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            seleccionar= newSelection;

        });
    }

    private void initDataBindingProductos() {
        columnNombreAnuncio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnDescripcionAnuncio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descProducto()));
        columnNameAnuncianteAnuncio.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().anunciante()));

        //tcNombreProducto.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
        //tcTipoProducto.setCellValueFactory(new PropertyValueFactory("tipoProducto"));
        //tcDescripcionProducto.setCellValueFactory(new PropertyValueFactory("descripcionProducto"));

    }
    private void initDataBindingPujas ()
    {
        columnNombreAnuncinatePuja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnDescripcionPuja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descProducto()));
        columnNombreAnuncinatePuja.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().anunciante()));
    }
}

