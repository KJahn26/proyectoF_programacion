package com.uniquindio.subastasUQ.controlle;

import com.uniquindio.subastasUQ.exceptions.*;
import com.uniquindio.subastasUQ.controlle.service.iModelFactoryController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import com.uniquindio.subastasUQ.mapping.mappings.SubastaMapper;
import com.uniquindio.subastasUQ.model.Producto;
import com.uniquindio.subastasUQ.model.SubastaUq;
import com.uniquindio.subastasUQ.model.Usuario;
import com.uniquindio.subastasUQ.utils.ArchivoUtil;
import com.uniquindio.subastasUQ.utils.Persistencia;
import com.uniquindio.subastasUQ.utils.subastaUqUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController implements iModelFactoryController {

    SubastaUq subastaUq;

    SubastaMapper mapper = SubastaMapper.INSTANCE;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    String fecha="";

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {

        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("invocación clase singleton");
        //cargarDatosBase();
        cargarResourceXML();
        //cargarDatosArchivos();
        //guardarResourceXML();
        //salvaGuardarDatosPrueba();
        //registrarAccionesSistema("Inicio del programa", 1, "inicio de sesion");


        if (subastaUq == null) {
            cargarDatosBase();
            guardarResourceXML();
            salvaGuardarDatosPrueba();


            registrarAccionesSistema("Inicio del programa", 1, "inicio de sesion");
        }

    }

    public void salvaGuardarDatosPrueba() {

        Persistencia.guardarUsuarios(getSubasta().getListaUsuarios());
    }

    private void cargarDatosBase() {

        subastaUq = subastaUqUtils.inicializarDatos();
    }


    @Override
    public void cargarDatosArchivos() {
        subastaUq = new SubastaUq();
        try {
            Persistencia.cargarDatosArchisvos(subastaUq);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public SubastaUq getSubasta() {

        return subastaUq;
    }

    public void setSubastaUq(SubastaUq SubastaUq) {

        this.subastaUq = SubastaUq;
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {

        return mapper.getUsuariosDto(subastaUq.getListaUsuarios());
    }

    public ArrayList<Usuario> coger() {
        return subastaUq.getListaUsuarios();
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try {
            if (!subastaUq.verificarUsuarioExistente(usuarioDto.cedula())) {
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getSubasta().agregarUsuario(usuario);
                registrarAccionesSistema("nuevo Usuario", 1, "se agrego a un usuario");
            }
            return true;
        } catch (UsuarioException e) {
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean eliminarUusario(String cedula) {
        boolean flagExiste = false;
        try {
            flagExiste = subastaUq.eliminarUsuario(cedula);
            registrarAccionesSistema("Usuario eliminado", 2, "se elimino a un usuario");
            System.out.println("El usuario se ah eliminado correctamente");

        } catch (UsuarioException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flagExiste;
    }

    @Override
    public boolean actualizarUsuario(String cedulaActual, UsuarioDto usuriosDto) {
        try {
            Usuario usuario = mapper.usuarioDtoToUsuario(usuriosDto);
            getSubasta().actualizarUsuario(cedulaActual, usuario);
            registrarAccionesSistema("Actualizacion Usuario", 2, "se Actualizo a un usuario");
            return true;
        } catch (UsuarioException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean buscarEmpleado() {
        return false;
    }

    private void cargarResourceXML() {
        subastaUq = Persistencia.cargarRecursoBancoXML();
    }

    private void guardarResourceXML() {

        Persistencia.guardarRecursoBancoXML(subastaUq);
    }

    private void cargarResourceBinario() {
        subastaUq = Persistencia.cargarRecursoBancoBinario();
    }

    private void guardarResourceBinario() {
        Persistencia.guardarRecursoBancoBinario(subastaUq);
    }

    public void registrarAccionesSistema(String mensajeLog, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensajeLog, nivel, accion);
    }

    public List<ProductoDto> obtenerProductos() {

        return mapper.getProductosDto(subastaUq.getListaproductos());
    }

    public boolean eliminarProducto(String nombre) {
        boolean flag = false;
        try {
            flag = subastaUq.eliminarProducto(nombre);
            guardarResourceXML();
            registrarAccionesSistema("Producto elimnado", 2, "se elimino a un producto debido a su compra");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public String cogerFecha() {

        return ArchivoUtil.cargarFechaSistema();
    }
    public boolean agregarProducto (ProductoDto productoDto) {
        boolean centinela=false;
        Producto producto = mapper.productoDtoToProducto(productoDto);
        if (!subastaUq.verificarProductoExiste(producto))
        {
            try {
                subastaUq.agregarProducto(producto);
                centinela=true;
                guardarResourceXML();
            } catch (UsuarioException e) {
                throw new RuntimeException(e);
            }


        }
      return centinela;
    }

}


