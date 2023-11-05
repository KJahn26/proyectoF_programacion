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



    public List<ProductoDto> obtenerProducto() {
        return modelFactoryController.obtenerProductos();
    }

    public boolean eliminarProducto(String s) {
        return modelFactoryController.eliminarProducto(s);
    }

    public String obtenerFecha(){
        return modelFactoryController.getFecha();
    }

    @Override
    public boolean guardarProducto(ProductoDto productoDto) {
        return modelFactoryController.agregarProducto(productoDto);
    }
}

