package com.uniquindio.subastasUQ.model;

import com.uniquindio.subastasUQ.exceptions.UsuarioException;
import com.uniquindio.subastasUQ.model.service.ISubastaUQService;

import java.util.ArrayList;

public class SubastaUq implements ISubastaUQService {

    ArrayList<Usuario> listaUsuarios= new ArrayList<>();

    public SubastaUq(){

    }

    public ArrayList<Usuario> getListaUsuarios() {return listaUsuarios;}

    public boolean verificarUsuarioExistente(String cedula) throws UsuarioException {
        if(usuarioExiste(cedula)){
            throw new UsuarioException("El empleado con cedula: "+cedula+" ya existe");
        }else{
            return false;
        }
    }

    public void agregarUsuario(Usuario nuevoUsuario) throws UsuarioException{
        getListaUsuarios().add(nuevoUsuario);
    }

    public boolean usuarioExiste(String cedula) {
        boolean UsuarioEncontrado = false;
        for (Usuario usuario : getListaUsuarios()) {
            if(usuario.getCedula().equalsIgnoreCase(cedula)){
                UsuarioEncontrado = true;
                break;
            }
        }
        return UsuarioEncontrado;
    }

}
