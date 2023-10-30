package com.uniquindio.subastasUQ.controlle;

import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;

import java.util.List;

public class UsuarioController {
    ModelFactoryController modelFactoryController;

    public UsuarioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    public List<UsuarioDto> obtenerEmpleados() {
        return modelFactoryController.obtenerUsuarios();
    }

    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        return modelFactoryController.agregarUsuario(usuarioDto);
    }

    /*public boolean eliminarEmpleado(String cedula) {
        return modelFactoryController.eliminarEmpleado(cedula);
    }*/


    /*public boolean actualizarEmpleado(String cedulaActual, EmpleadoDto empleadoDto) {
        return modelFactoryController.actualizarUsuario(cedulaActual, empleadoDto);
    }*/
}
