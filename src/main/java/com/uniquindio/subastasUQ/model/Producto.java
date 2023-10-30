package com.uniquindio.subastasUQ.model;

public class Producto
{
    private String tipoProducto ="";
    private String nombreProducto ="";
    private String descrpcionProducto="";

    public Producto ()
    {}

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getDescrpcionProducto() {
        return descrpcionProducto;
    }

    public void setDescrpcionProducto(String descrpcionProducto) {
        this.descrpcionProducto = descrpcionProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

}
