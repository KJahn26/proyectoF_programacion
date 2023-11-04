package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.controlle.UsuarioController;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class administracionUsuarioController

{
        UsuarioController usuarioControllerService;
        ObservableList<UsuarioDto> listaUsuarios= FXCollections.observableArrayList();
        UsuarioDto usuariosSeleccionado;
        @FXML
        private Button BUscar;

        @FXML
        private Button ActualizarTabla;
        @FXML
        private Button ActualizarUsuario;

        @FXML
        private Button Eliminar;

        @FXML
        private Button Agregar;

        @FXML
        private TextField txtNombre;

        @FXML
        private TextField txtTelefono;

        @FXML
        private TextField txtCedula;

        @FXML
        private TextField txtDireccion;

        @FXML
        private TextField txtE_mail;
        @FXML
        private TextField txtContraseña;

        @FXML
        private TableView<UsuarioDto> tableUsuarios;

        @FXML
        private TableColumn<UsuarioDto,String>tableId;

        @FXML
        private TableColumn<UsuarioDto, String> tableNombre;

        @FXML
        private TableColumn<UsuarioDto, String> tableTelefono;

        @FXML
        private TableColumn<UsuarioDto, String> tableCedula;

        @FXML
        private TableColumn<UsuarioDto, String> tableDireccion;

        @FXML
        private TableColumn<UsuarioDto, String> tableEmail;

        @FXML
        private TableColumn<UsuarioDto, String> tableActivo;

        @FXML
        void initialize(){
            usuarioControllerService =new UsuarioController();
            intiView();
        }

        private void intiView() {
                initDataBinding();
                obtenerUsuario();
                tableUsuarios.getItems().clear();
                tableUsuarios.setItems(listaUsuarios);
                listenerSelection();
        }

        private void initDataBinding() {
                tableNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
                tableCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
                tableDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
                tableEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email()));
                tableTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().telefono()));
        }

        private void obtenerUsuario() {

                listaUsuarios.addAll(usuarioControllerService.obtenerUsuario());
        }

        private void listenerSelection() {
                tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                        usuariosSeleccionado= newSelection;

                });
        }

        @FXML
        void ActualizarTablaAction(ActionEvent event) {
                initDataBinding();
                tableUsuarios.getItems().clear();
                tableUsuarios.setItems(listaUsuarios);
                obtenerUsuario();
                //listenerSelection();
        }
        @FXML
        void actualizarUsuarioAction(ActionEvent event) {
                actualizarUsuario();

        }

        @FXML
        void AgregarAction(ActionEvent event) {

                crearUsuario();
        }



        @FXML
        void EliminarAction(ActionEvent event) {
                eliminarUsuario();
        }

        private void crearUsuario(){
                UsuarioDto usuarioDto= new UsuarioDto(txtNombre.getText(),txtTelefono.getText(),txtE_mail.getText(),txtCedula.getText(),txtDireccion.getText(),txtContraseña.getText(),"");

                if(datosValidos(usuarioDto)){
                        if(usuarioControllerService.agregarUsuario(usuarioDto)){
                                listaUsuarios.add(usuarioDto);
                                mostrarMensaje("Notificación usuario", "usuario creado", "El usuario se ha creado con éxito", Alert.AlertType.INFORMATION);
                                limpiarCamposUsuario();
                        }else{
                                mostrarMensaje("Notificación usuario", "usuario creado", "El usuario no se ha creado con éxito", Alert.AlertType.ERROR);
                        }
                }else{
                        mostrarMensaje("Notificación usuario", "usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
                }
        }

        private void limpiarCamposUsuario() {
                txtNombre.setText("");
                txtDireccion.setText("");
                txtE_mail.setText("");
                txtCedula.setText("");
                txtTelefono.setText("");
                txtContraseña.setText("");
        }


        private boolean datosValidos(UsuarioDto usuarioDto) {
                String mensaje = "";
                if(usuarioDto.nombre() == null || usuarioDto.nombre().equals(""))
                        mensaje += "El nombre es invalido \n" ;
                if(usuarioDto.cedula() == null || usuarioDto.cedula().equals(""))
                        mensaje += "El documento es invalido \n" ;
                if (usuarioDto.direccion()==null || usuarioDto.direccion().equals(""))
                        mensaje+="La dirección no es valida \n";
                if (usuarioDto.telefono()==null || usuarioDto.telefono().equals(""))
                        mensaje+="El telefono no es valido \n";
                if (usuarioDto.email()==null || usuarioDto.email().equals(""))
                        mensaje+="Correo no es valido \n";
                if (usuarioDto.contrasena()==null || usuarioDto.contrasena().equals(""))
                        mensaje+="Contraseña es invalida \n";
                if(mensaje.equals("")){
                        return true;
                }else{
                        mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
                        return false;
                }
        }



    private void eliminarUsuario ()
    {
            boolean usuarioEliminado=false;
            if (usuariosSeleccionado!= null)
            {
                    if (mostrarMensajeConfirmacion("¿Estas seguro de elmininar al Usuario?"))

                    {
                            usuarioEliminado=usuarioControllerService.eliminarEmpleado(usuariosSeleccionado.cedula());
                            if (usuarioEliminado)
                            {
                                    listaUsuarios.remove(usuariosSeleccionado);
                                    usuariosSeleccionado=null;
                                    tableUsuarios.getSelectionModel().clearSelection();

                                    mostrarMensaje("Notificación usuario", "usuario eliminado", "El usuario se ha eliminado con éxito", Alert.AlertType.INFORMATION);


                            }
                            else
                            {
                                    mostrarMensaje("Notificación usuario", "usuario no eliminado", "El usuario no se puede eliminar", Alert.AlertType.ERROR);
                            }

                    }
                    else
                    {
                            mostrarMensaje("Notificación usuario", "usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
                    }

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

        private void actualizarUsuario ()
        {
                boolean usuarioActualizado =false;
                        //1.Capturar los datos
                String cedulaActual =usuariosSeleccionado.cedula();
                UsuarioDto usuarioDto = construirUsuarioDTo();
                        //2.verificar el usuario seleccionado
                if (usuariosSeleccionado!=null)
                {
                        //Validar la información
                        if (datosValidos(usuariosSeleccionado))
                        {
                                usuarioActualizado=usuarioControllerService.actualizarEmpleado(cedulaActual,usuarioDto);
                                if (usuarioActualizado)
                                {
                                        listaUsuarios.remove(usuariosSeleccionado);
                                        listaUsuarios.add(usuarioDto);
                                        tableUsuarios.refresh();
                                        mostrarMensaje("Notificación usuario", "usuario actualizado", "El usuario se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                                        limpiarCamposUsuario();
                                }
                                else
                                {
                                        mostrarMensaje("Notificación usurio", "usuario no actualizado", "El usurio no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                                }

                        }
                        else
                        {
                                mostrarMensaje("Notificación usuario", "usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
                        }

                }
        }

        private UsuarioDto construirUsuarioDTo() {
                return new UsuarioDto(
                        txtNombre.getText(),
                        txtTelefono.getText(),
                        txtE_mail.getText(),
                        txtCedula.getText(),
                        txtDireccion.getText(),
                        txtContraseña.getText(),
                        ""
                );
        }

}
