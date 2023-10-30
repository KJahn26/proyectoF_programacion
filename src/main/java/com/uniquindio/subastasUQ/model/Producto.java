package com.uniquindio.subastasUQ.model;

import java.io.Serializable;

public class Producto extends SubastaUq implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String nombreProducto ="";
    private String tipoProducto ="";
    private String descProducto ="";

    private String anunciante="";

    public Producto ()
    {}

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(String anunciante) {
        this.anunciante = anunciante;
    }

}
