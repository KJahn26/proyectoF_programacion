package com.uniquindio.subastasUQ.utils;

import com.uniquindio.subastasUQ.model.SubastaUq;
import com.uniquindio.subastasUQ.model.Usuario;

public class subastaUqUtils {

    public static SubastaUq inicializarDatos(){
        SubastaUq subastaUq= new SubastaUq();

        Usuario usuario = new Usuario();
        usuario.setNombre("Jean");
        usuario.setApellido("Mendez");
        usuario.setCedula("1120839058");
        usuario.setDireccion("peatonal UQ");
        usuario.setEmail("jeank.mendezc@uqvirtual.edu.co");
        usuario.setContraseña("megustas");
        usuario.setTelefono("3223936041");
        subastaUq.getListaUsuarios().add(usuario);

   return subastaUq; }
}
