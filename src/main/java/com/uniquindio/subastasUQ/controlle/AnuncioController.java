package com.uniquindio.subastasUQ.controlle;

import com.uniquindio.subastasUQ.controlle.service.IAnuncioService;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.PujaDto;

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

    public List<PujaDto> obtenerProductosPuja(){ return modelFactoryController.obtenerProductosPuja();}

    public boolean guardarPuja(PujaDto pujaDto){
        return modelFactoryController.agregarPuja(pujaDto);
    }

    public boolean eliminarpuja(String nombre){ return modelFactoryController.eliminarPuja(nombre);}

    public String obtenerFecha(){
        return modelFactoryController.getFecha();
    }

    @Override
    public boolean guardarProducto(ProductoDto productoDto) {
        return modelFactoryController.agregarProducto(productoDto);
    }
}

