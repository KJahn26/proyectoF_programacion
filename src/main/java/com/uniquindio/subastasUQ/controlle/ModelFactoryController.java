package com.uniquindio.subastasUQ.controlle;

import com.uniquindio.subastasUQ.exceptions.*;
import com.uniquindio.subastasUQ.controlle.service.iModelFactoryController;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import com.uniquindio.subastasUQ.mapping.mappings.SubastaMapper;
import com.uniquindio.subastasUQ.model.SubastaUq;
import com.uniquindio.subastasUQ.model.Usuario;
import com.uniquindio.subastasUQ.utils.subastaUqUtils;

import java.util.List;

public class ModelFactoryController implements iModelFactoryController {

    SubastaUq subastaUq;

    SubastaMapper mapper= SubastaMapper.INSTANCE ;

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("invocación clase singleton");
        cargarDatosBase();
    }

    private void cargarDatosBase() {
        subastaUq = subastaUqUtils.inicializarDatos();
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

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try{
            if(!subastaUq.verificarUsuarioExistente(usuarioDto.cedula())) {
                Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
                getSubasta().agregarUsuario(usuario);
            }
            return true;
        }catch (UsuarioException e){
            e.getMessage();
            return false;
        }
    }

    @Override
    public boolean eliminarUusario() {
        return false;
    }

    @Override
    public boolean actualizarUsuario() {
        return false;
    }

    @Override
    public boolean buscarEmpleado() {
        return false;
    }
}
