package com.uniquindio.subastasUQ.controller.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProductosController {
    @FXML
    private TableView<?> tableUsuarios;

    @FXML
    private TableColumn<?, ?> tableNombre;

    @FXML
    private TableColumn<?, ?> tableTelefono;

    @FXML
    private TableColumn<?, ?> tableCedula;

    @FXML
    private TableColumn<?, ?> tableDireccion;

    @FXML
    private TableColumn<?, ?> tableEmail;

    @FXML
    private TableColumn<?, ?> tableEmail1;

    @FXML
    private TableColumn<?, ?> tableEmail11;

    @FXML
    private TableColumn<?, ?> tableEmail111;

    @FXML
    private Button btnPujar;

    @FXML
    void actionPujar(ActionEvent event) {

    }
}
