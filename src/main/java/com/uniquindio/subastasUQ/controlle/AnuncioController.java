package com.uniquindio.subastasUQ.controlle;

import com.uniquindio.subastasUQ.controlle.service.IAnuncioService;
import com.uniquindio.subastasUQ.mapping.dto.AnuncioDto;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.PujaDto;
import com.uniquindio.subastasUQ.model.Anuncio;

import java.util.ArrayList;
import java.util.List;

public class AnuncioController implements IAnuncioService {

    ModelFactoryController modelFactoryController;

    public AnuncioController(){

        modelFactoryController = ModelFactoryController.getInstance();
        //consumirProductos();
    }


    public void consumirProductos(){
        modelFactoryController.consumirServicioProductos();
    }

    public List<ProductoDto> obtenerProducto(boolean flag) {
        return modelFactoryController.obtenerProductos(flag);
    }
    public List<ProductoDto> obtenerProductoAdquirido(boolean flag) {
        return modelFactoryController.obtenerProductosAdquiridos(flag);
    }

    public boolean eliminarProducto(String s) {
        return modelFactoryController.eliminarProducto(s);
    }

    public List<PujaDto> obtenerProductosPuja(boolean flag){ return modelFactoryController.obtenerProductosPuja(flag);}

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

    @Override
    public boolean guardarAnuncio(AnuncioDto anunciodto) {
        return modelFactoryController.agregarAnuncio(anunciodto);
    }


    public String getNombreComprador(){
        return modelFactoryController.getNombreComprador();
    }

    public String getNombreProducto(){ return modelFactoryController.getNombreProducto();}

    public void setNombreProducto(String nombreProducto){ modelFactoryController.setNombreProducto(nombreProducto);}

    public String getNombreAnunciante(){ return modelFactoryController.getNombreAnunciante();}

    public boolean setDatos (AnuncioDto anuncioDto)
        {
            return modelFactoryController.agregarAnuncio(anuncioDto);
        }

}

