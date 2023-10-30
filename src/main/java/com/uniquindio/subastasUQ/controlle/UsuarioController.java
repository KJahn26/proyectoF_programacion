package com.uniquindio.subastasUQ.controlle;

import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;

import java.util.List;

public class UsuarioController {
    ModelFactoryController modelFactoryController;

    public UsuarioController(){

        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<UsuarioDto> obtenerUsuario() {

        return modelFactoryController.obtenerUsuarios();
    }

    public boolean agregarUsuario(UsuarioDto usuarioDto) {

        return modelFactoryController.agregarUsuario(usuarioDto);
    }

    public boolean eliminarEmpleado(String cedula) {
        return modelFactoryController.eliminarUusario(cedula);
    }


    public boolean actualizarEmpleado(String cedulaActual, UsuarioDto usuarioDto) {
        return modelFactoryController.actualizarUsuario(cedulaActual, usuarioDto);
    }
    public String fecha ()
    {

        return  modelFactoryController.cogerFecha();
    }

    public void extraerfecha(String fecha){
       modelFactoryController.setFecha(fecha);
    }




}
