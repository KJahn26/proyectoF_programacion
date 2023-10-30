package com.uniquindio.subastasUQ.controller.view;

import com.uniquindio.subastasUQ.controlle.UsuarioController;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class administracionUsuarioController

{
        UsuarioController usuarioControllerService;
        ObservableList<UsuarioDto> listaUsuarios= FXCollections.observableArrayList();
        UsuarioDto usuariosSeleccionados;
        @FXML
        private Button BUscar;

        @FXML
        private Button Actualizar;

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
                obtenerEmpleados();
                tableUsuarios.getItems().clear();
                tableUsuarios.setItems(listaUsuarios);
                listenerSelection();
        }

        private void initDataBinding() {
                tableNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
                //tableId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().));
                tableCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
                tableDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
                tableEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().email()));
                tableTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().telefono()));
        }

        private void obtenerEmpleados() {
                listaUsuarios.addAll(usuarioControllerService.obtenerEmpleados());
        }

        private void listenerSelection() {
                tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                        usuariosSeleccionados = newSelection;
                        mostrarElementosDeLaTabla(usuariosSeleccionados);
                });
        }

        @FXML
        void ActualizarAction(ActionEvent event) {

        }

        @FXML
        void AgregarAction(ActionEvent event) {
                crearUsuario();
        }

        @FXML
        void BuscarAction(ActionEvent event) {

        }

        @FXML
        void EliminarAction(ActionEvent event) {

        }

        private void crearUsuario(){
                UsuarioDto usuarioDto= new UsuarioDto(txtNombre.getText(),txtTelefono.getText(),txtE_mail.getText(),txtCedula.getText(),txtDireccion.getText(),"aja");

                if(datosValidos(usuarioDto)){
                        if(usuarioControllerService.agregarUsuario(usuarioDto)){
                                listaUsuarios.add(usuarioDto);
                                mostrarMensaje("Notificación empleado", "Empleado creado", "El empleado se ha creado con éxito", Alert.AlertType.INFORMATION);
                                limpiarCamposEmpleado();
                        }else{
                                mostrarMensaje("Notificación empleado", "Empleado no creado", "El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
                        }
                }else{
                        mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
                }
        }

        private void limpiarCamposEmpleado() {
                txtNombre.setText("");
                txtDireccion.setText("");
                txtE_mail.setText("");
                txtCedula.setText("");
                txtTelefono.setText("");
        }


        private boolean datosValidos(UsuarioDto usuarioDto) {
                String mensaje = "";
                if(usuarioDto.nombre() == null || usuarioDto.nombre().equals(""))
                        mensaje += "El nombre es invalido \n" ;
                if(usuarioDto.cedula() == null || usuarioDto.cedula().equals(""))
                        mensaje += "El documento es invalido \n" ;
                if(mensaje.equals("")){
                        return true;
                }else{
                        mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
                        return false;
                }
        }

    //Sirve para seleccionar el usuario  de la tabla
    //Este metodo nos ayuda a mostrar todo lo que tenga ese usuario en la tabal
    private void mostrarElementosDeLaTabla (UsuarioDto usuarioSeleccionado)
    {
            if(usuarioSeleccionado != null){
                    txtNombre.setText(usuarioSeleccionado.nombre());
                    txtCedula.setText(usuarioSeleccionado.cedula());
                    txtTelefono.setText(usuarioSeleccionado.telefono());
                    txtE_mail.setText(usuarioSeleccionado.email());
                    txtDireccion.setText(usuarioSeleccionado.direccion());
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
