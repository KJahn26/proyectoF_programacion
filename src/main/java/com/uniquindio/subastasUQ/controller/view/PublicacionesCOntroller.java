package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.AnuncioDto;
import com.uniquindio.subastasUQ.model.Anuncio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class PublicacionesCOntroller {
    AnuncioController controllerAnuncio;
    ObservableList<AnuncioDto> listaAnuncios = FXCollections.observableArrayList();
@FXML
    private HBox HBoxTableAnuncios;

    @FXML
    private TableView<Anuncio> tableAnuncios;

    @FXML
    private TableColumn<Anuncio, String> ColumnCodigo;

    @FXML
    private TableColumn<Anuncio, String> ColumnDescripcion;

    @FXML
    private TableColumn<Anuncio, String> ColumnFechaInicio;

    @FXML
    private TableColumn<Anuncio, String> ColumnFechaFinal;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtGechaini;

    @FXML
    private TextField txtFechaFinal;

    @FXML
    private HBox HboxImagen;

    @FXML
    private ImageView ImageAnuncio;

    @FXML
    public void initialize ()
    {
        controllerAnuncio = new AnuncioController();
        init();
    }

    public void init ()
    {
        inicilizarDatos();
    }
    public void inicilizarDatos ()
    {

    }
 public void initDataBinding(){


    }


}

