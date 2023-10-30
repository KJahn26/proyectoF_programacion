package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroController {
    ObservableList<UsuarioDto> listaUsuarios = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;
    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnRegistrarse;
    @FXML
    private Button NUevo;

    @FXML
    private PasswordField Contraseña;

    @FXML
    private PasswordField ConfirmarContraseña;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtEmail;


    public void RegistrarAction (ActionEvent event)
    {
        crearUsuario();
    }
    public void crearUsuario () {

    }

    public void agregarUsuario ()
    {
        //1.Capturamos los datos
        UsuarioDto usuariosDto =construirEmpleadoDto ();
        //2.Validar la información
        if (datosValidos((usuariosDto)))
        {

        }

    }

    private UsuarioDto construirEmpleadoDto ()
    {
        return new UsuarioDto (
                txtNombre.getText(),
                txtTelefono.getText(),
                txtEmail.getText(),
                txtCedula.getText(),
                txtDireccion.getText(),
                Contraseña.getText()
        );

    }
    private boolean datosValidos (UsuarioDto usuariosDto)
    {
        String mensaje = "";
        if(usuariosDto.nombre() == null || usuariosDto.nombre().equals(""))
            mensaje += "El nombre es invalido \n" ;

        if(usuariosDto.cedula() == null || usuariosDto.cedula().equals(""))
            mensaje += "El documento es invalido \n" ;
        if(usuariosDto.contraseña()== null || usuariosDto.contraseña().equals(""))
            mensaje += "La contraseña  es invalida \n" ;
        if (usuariosDto.telefono()==null || usuariosDto.telefono().equals(""))
            mensaje+="EL telefono invalido";
        if (usuariosDto.email()==null || usuariosDto.email().equals(""))
            mensaje+="El correo es invalido";
        if (usuariosDto.direccion()==null || usuariosDto.direccion().equals(""))
            mensaje="LA dirección invalida";

        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }
    @FXML
    void NUevoAction(ActionEvent event) {
            txtNombre.setText(" Nombre");
            txtCedula.setText("Cedula");
            txtTelefono.setText("Telefono");
            txtDireccion.setText("Dirección");
            txtEmail.setText("E-mail");

    }

}
