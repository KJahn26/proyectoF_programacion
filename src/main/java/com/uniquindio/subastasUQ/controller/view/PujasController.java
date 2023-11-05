package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.HelloApplication;
import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.util.Optional;

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
    private Button btnRegresar;
    @FXML
    private TableColumn<ProductoDto, String> nombre;

    @FXML
    private Button btnEliminarPuja;

    @FXML
    private TextField txtxValorPuja;
    public void initialize() {
        anuncioController = new AnuncioController();
        initiew();

    }
    public void initiew ()
    {
        initDataBindingProductos();
        obtenerProductos();
        tableProductos.getItems().clear();
        tableProductos.setItems(listaProductos);
        initDataBindingPujas();
        tablePujas.getItems().clear();
        tablePujas.setItems(listaProductosPuja);
        //listenerSelection();
        NumberStringConverter converter = new NumberStringConverter();
        TextFormatter<Number> textFormatter = new TextFormatter<>(converter, 0, change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                return null;
            }
            return change;
        });

        txtxValorPuja.setTextFormatter(textFormatter);
        listenerSelection();
        eliminarProducto();
    }
    @FXML
    void ActionRegresar(ActionEvent event) {
        mostrarVentana(event,"hello-view.fxml","Subas universidad del quindio");
    }

    @FXML
    void ActionPujar(ActionEvent event) {
        listenerSelection();
        initDataBindingPujas();
        //listenerSelection();
        double centinela=0;
        centinela=Long.parseLong(seleccionar.valorInicial());
        if (centinela<=Long.parseLong(txtxValorPuja.getText()))
        {
            if (seleccionar!=null)
            {
                listaProductosPuja.addAll(seleccionar);
                tablePujas.setItems(listaProductosPuja);
            }
            else
            {
                mostrarMensaje("Notificación de producto", "Producto no seleccionado", "Por favor vuelva a seleccioanr el producto", Alert.AlertType.ERROR);

            }
        }
        else
        {
            mostrarMensaje("Notificación de producto", "La cantidad no es suficiiente", "tiene que aumentar la puja ", Alert.AlertType.ERROR);
        }



    }

    @FXML
    void actionEliminarPuja(ActionEvent event) {

        eliminarPuja();
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
        columnValorProductoAnuncio.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().valorInicial()));
        columnTipoProductoAnuncio.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().tipoProducto()));
        columnFechaFinalAnuncio.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().fechaTerminarPublicacion()));




    }
    private void initDataBindingPujas ()
    {
        nombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreProducto()));
        columnDescripcionPuja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descProducto()));
        columnNombreAnuncinatePuja.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().anunciante()));
        columValorProductoPuja.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().valorInicial()));
        columnTipoProductoPuja.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().tipoProducto()));
        columnFechaFInalPuja.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().fechaTerminarPublicacion()));
        columnFechaInicioPuja.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().fechaPublicacion()));

    }
    private void mostrarMensaje(String msj, String header, String contenido, Alert.AlertType alertType){
        Alert aler= new Alert(alertType);
        aler.setTitle(msj);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    public void eliminarPuja ()
    {
        if (seleccionar!=null)
        {
            listaProductosPuja.remove(seleccionar);
            //seleccionar = null;
            tablePujas.getSelectionModel().clearSelection();
        }
    }




    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }


    }

    public void eliminarProducto ()
    {
        LocalDate fecha = LocalDate.now();
        String centinela="";
        centinela=fecha.toString();
        //System.out.println(centinela);
        for(int i=0;i<listaProductos.size();i++)
       // for (ProductoDto s: listaProductos)
        {
            ProductoDto s = listaProductos.get(i);
            if (s.fechaTerminarPublicacion().equalsIgnoreCase(centinela))
            {
                anuncioController.eliminarProducto(s.nombreProducto());
                listaProductos.remove(s);
                i--;
                tableProductos.getSelectionModel().clearSelection();


                mostrarMensaje("Notificación de producto", "Producto eliminado", "Se elimino el producto " , Alert.AlertType.ERROR);

            }
        }
    }
    private void mostrarVentana (ActionEvent event, String ruta, String centinela)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(ruta));
            //loader.setLocation(HelloApplication.class.getResource(ruta));
            AnchorPane rootLayout  = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.setTitle(centinela);
            appStage.toFront();
            appStage.show();

        }catch (Exception e)
        {
            e.printStackTrace();

        }
    }


}

