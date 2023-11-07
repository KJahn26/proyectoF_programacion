package com.uniquindio.subastasUQ.controller.view;
import com.uniquindio.subastasUQ.HelloApplication;
import com.uniquindio.subastasUQ.controlle.AnuncioController;
import com.uniquindio.subastasUQ.controlle.UsuarioController;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.Optional;


public class controllerAgregarProducto {
    ObservableList<ProductoDto> listaProductos = FXCollections.observableArrayList();
    AnuncioController controllerAnuncio;
    UsuarioController usuarioControllerService;

    final FileChooser fileimg= new FileChooser();

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
    private Button btnRegresar;

    @FXML
    void initialize() {
        controllerAnuncio =  new AnuncioController();
        inicializarComboBOx();
        txtFechaPublicación.setText(fechaPublicación());
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

        try{
        File file= fileimg.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

            String url= file.toURI().toURL().toString();
            System.out.print(url);
            Image imgload= new Image(url);
            miImageview.setImage(imgload);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }

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
            mostrarMensajeConfirmacion("producto agregado correctamente");
            }
        }




    }
    private String cogerFecha ()
    {
        String centinela="";
        LocalDate fecha1 = dickerFechaTerminaPublicacion.getValue();
        centinela=fecha1.toString();
       return centinela;
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
    @FXML
    void ActionRegresar(ActionEvent event) {
        mostrarVentana(event,"hello-view.fxml","Subas universidad del quindio");

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

    public String fechaPublicación ()
    {

        usuarioControllerService = new UsuarioController();
        return usuarioControllerService.fecha();
    }
}
