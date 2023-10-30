package com.uniquindio.subastasUQ.mapping.mappings;

import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import com.uniquindio.subastasUQ.model.Producto;
import com.uniquindio.subastasUQ.model.Usuario;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SubastaMapper {
    SubastaMapper INSTANCE = Mappers.getMapper(SubastaMapper.class);

    @Named("usuarioToUsuarioDto")
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    @IterableMapping(qualifiedByName = "usuarioToUsuarioDto")
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);

    //@Named("ProductoToProductoDto")
    ProductoDto ProductoToproductoDto(Producto producto);

    Producto productoDtoToProducto(ProductoDto productoDto);

    //@IterableMapping(qualifiedByName = "productoToProductoDto")
    List<ProductoDto> getProductosDto(ArrayList<Producto> listaproductos);
}
