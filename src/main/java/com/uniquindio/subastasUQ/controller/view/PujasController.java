package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.NumberStringConverter;

public class PujasController {

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

    }

    @FXML
    void actionEliminarPuja(ActionEvent event) {

    }
}
