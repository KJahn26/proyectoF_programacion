package com.uniquindio.subastasUQ.controller.view;
import com.uniquindio.subastasUQ.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class controllerAgregarProducto {
    @FXML
    private TextField txtNombreProducto;

    @FXML
    private ComboBox<String> comboBoxTiṕoProducto;

    @FXML
    private TextField txtDescripcionProducto;
    @FXML
    private TextField txtValorInicialProducto;

    @FXML
    private Button btnPublicar;

    @FXML
    private Button btnVaciarEspacios;
    @FXML
    private Button btnRegresar;
    @FXML
    void initialize()
    {
        inicializarComboBox();
        NumberStringConverter converter = new NumberStringConverter();
        TextFormatter<Number> textFormatter = new TextFormatter<>(converter, 0, change -> {
            if (!change.getControlNewText().matches("\\d*")) {
                return null;
            }
            return change;
        });

        txtValorInicialProducto.setTextFormatter(textFormatter);
    }

    @FXML
     private void RegresarAction(ActionEvent event) {
    mostrarVentana(event,"hello-view.fxml","Subas uq");

    }

    @FXML
    private void actionPublicar(ActionEvent event) {

       String centinela =((String) comboBoxTiṕoProducto.getValue());
       System.out.println(centinela);
    }

    @FXML
    private void actionVaciarEspacios(ActionEvent event) {
        vaciarEspacios();

    }

    public void mostrarVentana (ActionEvent event,String ruta,String centinela)
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
    private void vaciarEspacios ()
    {
        txtDescripcionProducto.setText("");
        txtNombreProducto.setText("");
        txtValorInicialProducto.setText("");

    }

    private void inicializarComboBox ()
    {
        comboBoxTiṕoProducto.getItems().addAll(
          "Tecnologia",
                    "Hogar",
                    "Deportes",
                    "Vehiculos",
                    "Bien raiz"
        );

    }

}
