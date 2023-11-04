package com.uniquindio.subastasUQ.controller.view;
import com.uniquindio.subastasUQ.HelloApplication;
import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;


public class controllerAgregarProducto {
    ObservableList<ProductoDto> listaProductos = FXCollections.observableArrayList();
    AnuncioController controllerAnuncio;
    @FXML
    private AnchorPane anchorAbajo;

    @FXML
    private HBox hboxSuperior;

    @FXML
    private HBox hboxMedio;

    @FXML
    private HBox hboxtextInferior;

    @FXML
    private HBox hboxImage;

    @FXML
    private ImageView miImageview;

    @FXML
    private HBox hboxAgregarImagen;

    @FXML
    private Button btnAgregarImagen;

    @FXML
    private HBox hboxTipoProducto;

    @FXML
    private ComboBox<String> comboBoxTipoProducto;

    @FXML
    private HBox hboxNombreAnunciante;

    @FXML
    private TextField txtNombreAnunciante;

    @FXML
    private HBox hboxFechaTerminaPublicacion;

    @FXML
    private DatePicker dickerFechaTerminaPublicacion;

    @FXML
    private HBox hboxValorInicial;

    @FXML
    private TextField txtValorInicial;

    @FXML
    private HBox hboxNombreProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private HBox hboxDescripcionProducto;

    @FXML
    private TextField txtDescripcionProducto;

    @FXML
    private HBox hboxFechapublicacion;

    @FXML
    private TextField txtFechaPublicación;

    @FXML
    private HBox hboxPublicarProducto;

    @FXML
    private Button btnPublicarProducto;

    @FXML
    void initialize() {
        controllerAnuncio =  new AnuncioController();
        inicializarComboBOx();
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("^\\d*$")) {
                return change;
            }
            return null;
        });

        txtValorInicial.setTextFormatter(textFormatter);

}

    @FXML
    void ActionAgregarImagen(ActionEvent event) {

    }

    @FXML
    void ActionPublicarProducto(ActionEvent event) {
        crearproductoDto();

    }

    private void inicializarComboBOx ()
    {

         comboBoxTipoProducto.getItems().addAll(
                 "Tecnología",
                 "Hogar",
                 "Bien raiz",
                 "Deportes",
                 "Vehiculos"
         );


    }
    public String cogerDatosComboBox ()
    {
        String centinela="";
        centinela=((String)comboBoxTipoProducto.getSelectionModel().getSelectedItem());
        return centinela;
    }
    private void crearproductoDto ()
    {
        ProductoDto productoDto = new ProductoDto(
                txtNombreProducto.getText(),
                cogerDatosComboBox(),
                txtDescripcionProducto.getText(),
                txtNombreAnunciante.getText(),
                txtValorInicial.getText(),
                txtFechaPublicación.getText(),
                cogerFecha()

        );

        if(datosValidos(productoDto))
        {
            if (controllerAnuncio.guardarProducto(productoDto));
            {
            listaProductos.add(productoDto);
            }
        }




    }
    private String cogerFecha ()
    {
        LocalDate fecha1 = dickerFechaTerminaPublicacion.getValue();

       return fecha1.toString();
    }





    private boolean datosValidos(ProductoDto productoDto) {
        String mensaje = "";
        if(productoDto.tipoProducto()==null || productoDto.tipoProducto().equals(""))
            mensaje += "EL tipo de producto no valido\n" ;
        if(productoDto.nombreProducto() == null || productoDto.nombreProducto().equals(""))
            mensaje += "Nombre del producto no valido \n" ;
        if (productoDto.descProducto()==null || productoDto.descProducto().equals(""))
            mensaje+="La descripcion del producto no valida \n";
        if (productoDto.anunciante()==null || productoDto.anunciante().equals(""))
            mensaje+="Nombre de anunciante no valido \n";
        if (productoDto.fechaPublicacion()==null || productoDto.fechaPublicacion().equals(""))
            mensaje+="fecha de publicacion no valida \n";
        if (productoDto.fechaTerminarPublicacion()==null || productoDto.fechaTerminarPublicacion().equals(""))
           mensaje+="La fecha final es invalida";
        if (productoDto.valorInicial()==null || productoDto.valorInicial().equals(""))
            mensaje+="EL valor inicial no es el indicado";
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }
    private void mostrarMensaje(String msj, String header, String contenido, Alert.AlertType alertType){
        Alert aler= new Alert(alertType);
        aler.setTitle(msj);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
}
