package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.PujaDto;
import com.uniquindio.subastasUQ.model.Puja;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class controllerDePujasUsuario {

    ObservableList<PujaDto> listaPujas = FXCollections.observableArrayList();
    AnuncioController anuncioController;

    @FXML
    private HBox hboxPujas;

    @FXML
    private TableView<PujaDto> tablePujas;

    @FXML
    private TableColumn<PujaDto, String> columnComprador;

    @FXML
    private TableColumn<PujaDto, String> columnFechapuja;

    @FXML
    private TableColumn<PujaDto, String> columnNombreAnunciante;

    @FXML
    private TableColumn<PujaDto, String> columnNombreProducto;

    @FXML
    private TableColumn<PujaDto, String> columnValorPuja;

    @FXML
    private TextField txtFechaPuja;

    @FXML
    private TextField txtNOmbreUsuario;

    @FXML
    private TextField txtNombreAnunciante;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtValorPuja;

    @FXML
    void initialize(){
        anuncioController= new AnuncioController();
        inicializar();
    }

    void inicializar(){
        initDataBinding();
        obtenerPujas();
        tablePujas.getItems().clear();
        tablePujas.setItems(listaPujas);
    }

    @FXML
    void RegresarAction(ActionEvent event) {


    }

    void initDataBinding(){
        columnComprador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreComprador()));
        columnNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnNombreAnunciante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreAnunciante()));
        columnFechapuja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaFinal()));
        columnValorPuja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().valorPuja()));


    }

    void obtenerPujas(){
        listaPujas.addAll(anuncioController.obtenerProductosPuja());
    }

}