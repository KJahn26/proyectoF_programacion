package com.uniquindio.subastasUQ.model;

import com.uniquindio.subastasUQ.exceptions.UsuarioException;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.model.service.ISubastaUQService;

import java.io.Serializable;
import java.util.ArrayList;

public class SubastaUq implements Serializable,ISubastaUQService {
    private static final long serialVersionUID = 1L;

    ArrayList<Usuario> listaUsuarios= new ArrayList<>();

    ArrayList<Producto> listaproductos= new ArrayList<>();

    public SubastaUq(){

    }

    public ArrayList<Usuario> getListaUsuarios()
    {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

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


    @Override
    public Usuario obtenerUsuario(String cedula) {
        Usuario usuarioEncontrado = null;
        for (Usuario empleado : getListaUsuarios()) {
            if(empleado.getCedula().equalsIgnoreCase(cedula)){
                usuarioEncontrado = empleado;
                break;
            }
        }
        return usuarioEncontrado;
    }

    @Override
    public Boolean eliminarUsuario(String cedula) throws UsuarioException {
        Usuario empleado = null;
        boolean flagExiste = false;
        empleado = obtenerUsuario(cedula);
        if(empleado == null)
            throw new UsuarioException("El usuario a eliminar no existe");
        else{
            getListaUsuarios().remove(empleado);
            flagExiste = true;
        }
        return flagExiste;
    }

    @Override
    public ArrayList<Usuario> obtenerEmpleados() {
        return listaUsuarios;
    }

    @Override
    public boolean actualizarUsuario(String cedulaActual, Usuario usurios) throws UsuarioException {
        Usuario usuarioActual = obtenerUsuario(cedulaActual);
        if(usuarioActual == null)
            throw new UsuarioException("El empleado a actualizar no existe");
        else{
            usuarioActual.setNombre(usurios.getNombre());
            usuarioActual.setCedula(usurios.getCedula());
            usuarioActual.setTelefono(usurios.getTelefono());
            usuarioActual.setEmail(usurios.getEmail());
            usuarioActual.setDireccion(usurios.getDireccion());
            usuarioActual.setContrasena(usurios.getContrasena());
            return true;
        }
    }

    public ArrayList<Producto> getListaproductos() {
        return listaproductos;
    }

    public void setListaproductos(ArrayList<Producto> listaproductos) {
        this.listaproductos = listaproductos;
    }

    public Producto obtenerProducto(String nombre){
        Producto pr= null;
        for(Producto p: getListaproductos()){
            if(p.getNombreProducto().equalsIgnoreCase(nombre)){
                pr=p;
                break;
            }
        }
        return pr;
    }

    public boolean eliminarProducto(String nombre) throws Exception {
        Producto pr = null;
        boolean flagExiste = false;
        pr = obtenerProducto(nombre);
        if(pr == null){
            throw new Exception("El Producto a eliminar no existe");
        }
        else{
            getListaproductos().remove(pr);
            flagExiste = true;
        }
        return flagExiste;
    }

    public boolean verificarProductoExiste (Producto producto)
    {
        boolean centinela=false;
        productoExiste(producto);
        return centinela;
    }
    public boolean productoExiste (Producto producto)
    {
        boolean centinela=false;
        for (Producto s: getListaproductos())
        {
            if (s.getTipoProducto().equals(producto.getTipoProducto()) && s.getNombreProducto().equals(producto.getNombreProducto())&&
            s.getDescProducto().equals(producto.getDescProducto()) && s.getAnunciante().equals(producto.getAnunciante()) )
            {

            }
            else
            {
                centinela=true;
            }
        }
        return centinela;
    }
    public void agregarProducto(Producto nuevoProducto) throws UsuarioException{
        getListaproductos().add(nuevoProducto);
    }

}
