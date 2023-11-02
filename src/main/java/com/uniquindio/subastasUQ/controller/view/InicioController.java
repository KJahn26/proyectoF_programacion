package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.HelloApplication;
import com.uniquindio.subastasUQ.controlle.ModelFactoryController;
import com.uniquindio.subastasUQ.controlle.UsuarioController;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import com.uniquindio.subastasUQ.model.Usuario;
import com.uniquindio.subastasUQ.utils.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;


public class InicioController {


    UsuarioController usuarioControllerService;
    ObservableList<UsuarioDto> listaUsuarios= FXCollections.observableArrayList();
    UsuarioDto usuariosSeleccionados;

    @FXML
    private Hyperlink olvidoContraseña;
@FXML
private TextField txtIngresoEmail;
@FXML
private PasswordField txtIngresoContraseña;
@FXML
private Button Acceder;
@FXML
private CheckBox CheckAnunciante;

@FXML
private CheckBox CheckComprador;
    @FXML
    void initialize(){
        usuarioControllerService =new UsuarioController();
        obtenerUsuario();
    }



    private void mostrarMensaje(String msj, String header, String contenido, Alert.AlertType alertType){
        Alert aler= new Alert(alertType);
        aler.setTitle(msj);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }



    @FXML
    void recuperarContraseñaAction(ActionEvent event)
    {


    }



    private void mostrarVentana (MouseEvent event,String ruta,String centinela)
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


    public boolean  validarAcceso ()
    {
        boolean centinela=true;
        if (txtIngresoEmail.getText()==null || txtIngresoEmail.getText().equals(""))
        {
            centinela=false;
            mostrarMensaje("Notificación ingreso", "Acceso denegado", " Hay un error a la hora de digitar los datos del correo", Alert.AlertType.ERROR);
        }
        else if (txtIngresoContraseña.getText()==null || txtIngresoContraseña.getText().equals(""))
        {
            centinela=false;

            mostrarMensaje("Notificación ingreso", "Acceso denegado", " Hay un error a la hora de digitar la contraseña", Alert.AlertType.ERROR);
        }
        vaciarCampos();

        return centinela;

    }

    public void vaciarCampos ()
    {
        txtIngresoEmail.setText("");
        txtIngresoContraseña.setText("");
    }

    @FXML
    private  String CompradorMauseClick(javafx.scene.input.MouseEvent mouseEvent) {

        String centinela="";

        if (CheckAnunciante.isSelected())
        {
            centinela="anunciante";
            CheckComprador.setSelected(false);
            CheckComprador.setDisable(false);
        }
        else if (CheckComprador.isSelected())
        {
            centinela="comprador";
            CheckAnunciante.setSelected(false);
            CheckAnunciante.setDisable(false);
        }

        else
        {
            centinela="no";
        }

    return centinela;
    }


    public void   Seleccionar(MouseEvent event) {
        String centinela="";
        String ayuda=txtIngresoEmail.getText();
         System.out.println(txtIngresoEmail.getText()+ " " +usuarioControllerService.fecha());
         usuarioControllerService.extraerfecha(cogerDatosInicio());
           if (validarDatos()!=false)
           {
               if (validarAcceso()!=false)
               {
                   centinela=CompradorMauseClick(event);
                   if (centinela.equals("anunciante"))
                   {
                       mostrarVentana(event,"Anunciante.fxml","Anunciante");
                       Persistencia.guardaRegistroLog("Inicio de Sesion",1,"Inicio de sesion como anunciante de "+ayuda);
                   }
                   else if (centinela.equals("comprador"))
                   {
                       mostrarVentana(event,"Comprador.fxml","Comprador");
                       Persistencia.guardaRegistroLog("Inicio de Sesion",1,"inicio de sesion como comprador de "+ayuda );
                   }
                   else if(centinela.equals("no"))
                   {
                       mostrarMensaje("Notificación opción", "Falta opción seleccionar", "No se puede dejar opciones sin seleccioanr", Alert.AlertType.ERROR);
                   }
               }

           }
           else
           {
               //mostrarMensaje("Notificación acceso", "Acceso denegado", "datos", Alert.AlertType.ERROR);
           }

       }


    public boolean validarDatos ()
    {

        return leerDatos();
    }
    public boolean leerDatos ()
    {
        boolean centinela=false;
        for (UsuarioDto s: listaUsuarios)
        {
            if (s.contrasena().equals(txtIngresoContraseña.getText()))
            {
                if (s.email().equals(txtIngresoEmail.getText()))
                {
                    centinela=true;
                }

            }

        }
        return centinela;
    }
    private void obtenerUsuario() {

        listaUsuarios.addAll(usuarioControllerService.obtenerUsuario());
    }

    public String cogerDatosInicio ()
    {
        String centinela="";
        centinela+="El usuario identificado con correo "+ txtIngresoEmail.getText()+" y "+" contraseña " + txtIngresoContraseña.getText();
        return centinela;
    }
}
