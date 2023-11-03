package com.uniquindio.subastasUQ.controller.view;
import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
public class ProductosController {
    AnuncioController anuncioControllerService;
    ProductoDto productoSeleccionado;
    ObservableList<ProductoDto> listaProductos= FXCollections.observableArrayList();
    @FXML
    private TableView<ProductoDto> tableUsuarios;

    @FXML
    private TableColumn<ProductoDto,String> columnNumeral;

    @FXML
    private TableColumn<ProductoDto, String> columnNombre;

    @FXML
    private TableColumn<ProductoDto, String> columnTipoProducto;

    @FXML
    private TableColumn<ProductoDto, String> columnDescripcion;

    @FXML
    private TableColumn<ProductoDto, String> columnNombreAnunciante;

    @FXML
    private TableColumn<ProductoDto, String> columnFechaInicio;

    @FXML
    private TableColumn<ProductoDto, String> columnFechaFinal;

    @FXML
    private TableColumn<ProductoDto, String> columnValorInicial;

    @FXML
    private Button btnPujar;
    @FXML
    void initialize ()
    {
        anuncioControllerService = new AnuncioController();
        initDataBinding();
        obtenerDatos();
        tableUsuarios.getItems().clear();
        tableUsuarios.setItems(listaProductos);

    }

    private void listenerSelection() {
        tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
        });
    }
    public void obtenerDatos ()
    {
        listaProductos.addAll(anuncioControllerService.obtenerProducto());
    }
    private void initDataBinding() {
        columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descProducto()));
        columnNombreAnunciante.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().anunciante()));

        //tcNombreProducto.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
        //tcTipoProducto.setCellValueFactory(new PropertyValueFactory("tipoProducto"));
        //tcDescripcionProducto.setCellValueFactory(new PropertyValueFactory("descripcionProducto"));

    }

    @FXML
    void actionPujar(ActionEvent event) {
        mostrarMensaje("Notificación usuario", "usuario no a iniciado sesión o no esta registrado", "por favor ingrese sesión o registrese para poder hacer pujas", Alert.AlertType.ERROR);

    }
    private void mostrarMensaje(String msj, String header, String contenido, Alert.AlertType alertType){
        Alert aler= new Alert(alertType);
        aler.setTitle(msj);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }


}
