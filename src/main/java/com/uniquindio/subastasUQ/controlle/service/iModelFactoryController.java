package com.uniquindio.subastasUQ.controlle.service;

import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;

import java.util.List;

public interface iModelFactoryController {

 List<UsuarioDto> obtenerUsuarios();

boolean agregarUsuario(UsuarioDto usuarioDto);

boolean eliminarUusario();

boolean actualizarUsuario();

boolean buscarEmpleado();


}
