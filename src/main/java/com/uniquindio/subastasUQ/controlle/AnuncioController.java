package com.uniquindio.subastasUQ.controlle;

import com.uniquindio.subastasUQ.controlle.service.IAnuncioService;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;

import java.util.List;

public class AnuncioController implements IAnuncioService {

    ModelFactoryController modelFactoryController;

    public AnuncioController(){

        modelFactoryController = ModelFactoryController.getInstance();
    }
    @Override
    public void fechaPublicacion() {

    }

    @Override
    public void fechaFinalizacion() {

    }

    @Override
    public void valorInicial() {

    }

    public List<ProductoDto> obtenerProducto() {
        return modelFactoryController.obtenerProductos();
    }

    public boolean eliminarProducto(String s) {
        return modelFactoryController.eliminarProducto(s);
    }

    public String obtenerFecha(){
        return modelFactoryController.getFecha();
    }

}

