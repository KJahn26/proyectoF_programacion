package com.uniquindio.subastasUQ.controlle;

import com.rabbitmq.client.DeliverCallback;
import com.uniquindio.subastasUQ.config.RabbitFactory;
import com.uniquindio.subastasUQ.exceptions.*;
import com.uniquindio.subastasUQ.controlle.service.iModelFactoryController;
import com.uniquindio.subastasUQ.mapping.dto.ProductoDto;
import com.uniquindio.subastasUQ.mapping.dto.PujaDto;
import com.uniquindio.subastasUQ.mapping.dto.UsuarioDto;
import com.uniquindio.subastasUQ.mapping.mappings.SubastaMapper;
import com.uniquindio.subastasUQ.model.Producto;
import com.uniquindio.subastasUQ.model.Puja;
import com.uniquindio.subastasUQ.model.SubastaUq;
import com.uniquindio.subastasUQ.model.Usuario;
import com.uniquindio.subastasUQ.utils.ArchivoUtil;
import com.uniquindio.subastasUQ.utils.Persistencia;
import com.uniquindio.subastasUQ.utils.subastaUqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController implements iModelFactoryController,Runnable {

    SubastaUq subastaUq;

    SubastaMapper mapper = SubastaMapper.INSTANCE;

    public final String QUEUE ="persistenciaUsuario";

    String fecha="";

    String nombreAnunciante="";

    String nombreProducto="";

    String nombreComprador="";

    RabbitFactory rabbitFactory;

    ConnectionFactory connectionFactory;

    Thread hiloServicioUsuarios;

    Thread hiloServicioProductos;

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    public static ModelFactoryController getInstance() {

        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("invocación clase singleton");
        //cargarDatosBase();
        //agregarDatos();
        cargarResourceXML();
        //cargarDatosArchivos();
        //guardarResourceXML();
        //salvaGuardarDatosPrueba();
        initRabbitConnection();
        consumirServicioUsuarios();
        consumirServicioProductos();



        if (subastaUq == null) {
            cargarDatosBase();
            guardarResourceXML();
            salvaGuardarDatosPrueba();



        }
        registrarAccionesSistema("Sin identificar Tipo de Usuario ", 1, "inicio el programa","InicioSesion");
    }

    @Override
    public void run(){
        Thread currentThread= Thread.currentThread();
        if(hiloServicioUsuarios==currentThread){
            consumirUsuarios();
        }
        if(hiloServicioProductos==currentThread){
            consumirProductos();
        }

    }
    public void consumirUsuarios() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Mensaje recibido: " + message);
                Usuario us= new Usuario();
                us.setNombre(message.split("@")[0]);
                us.setCedula(message.split("@")[1]);
                us.setTelefono(message.split("@")[2]);
                us.setDireccion(message.split("@")[3]);
                us.setEmail(message.split("@")[4]);
                us.setContrasena(message.split("@")[5]);
                try {
                    if(us.verificarUsuarioExistente(message.split("@")[1])){
                        getSubasta().agregarUsuario(us);
                        guardarResourceXML();
                        cargarResourceXML();
                    }
                } catch (UsuarioException e) {
                    throw new RuntimeException(e);
                }
            };
            while (true) {
                channel.basicConsume(QUEUE, true, deliverCallback, consumerTag -> { });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumirProductos(){
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody());
                System.out.println("Mensaje recibido: " + message);
                Producto us= new Producto();
                us.setNombreProducto(message.split("@")[0]);
                us.setTipoProducto(message.split("@")[1]);
                us.setDescProducto(message.split("@")[2]);
                us.setAnunciante(message.split("@")[3]);
                us.setValorInicial(message.split("@")[4]);
                us.setFechaPublicacion(message.split("@")[5]);
                us.setFechaTerminarPublicacion(message.split("@")[6]);
                us.setFechaAdquirido(message.split("@")[7]);
                try {
                    if(us.verificarProductoExiste(us)){
                        getSubasta().agregarProducto(us);
                        guardarResourceXML();
                        cargarResourceXML();

                    }
                } catch (ProductoException e) {
                    throw new RuntimeException(e);
                }
            };
            while (true) {
                channel.basicConsume(QUEUE, true, deliverCallback, consumerTag -> { });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initRabbitConnection() {
        rabbitFactory = new RabbitFactory();
        connectionFactory = rabbitFactory.getConnectionFactory();
        System.out.println("conexion establecida con rabbitMQ");
    }

    public void consumirServicioUsuarios(){
        hiloServicioUsuarios = new Thread(this);
        hiloServicioUsuarios.start();
    }

    public void consumirServicioProductos(){
        hiloServicioProductos= new Thread();
        hiloServicioProductos.start();
    }

    public void producirUsuarios(String message){
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE, false, false, false, null);
            channel.basicPublish("", QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void producirProductos(String message){
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE, false, false, false, null);
            channel.basicPublish("", QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
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
                String msj=usuario.getNombre() + "@" + usuario.getCedula() + "@" + usuario.getTelefono() +
                        "@" + usuario.getDireccion() + "@" + usuario.getEmail() + "@" + usuario.getContrasena() + "\n";
                producirUsuarios(msj);
                guardarResourceXML();
                cargarResourceXML();
                registrarAccionesSistema("Sin identificar", 1, "agrego a un usuario","RegistroUsuario");
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
            registrarAccionesSistema("Sin identificar", 2, "elimino a un usuario","AdministracionUsuarios");
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
            registrarAccionesSistema("Sin identificar", 2, "Actualizo a un usuario","AdministracionUsuarios");
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

    public void registrarAccionesSistema(String tipoUsuario, int nivel, String accion,String interfaz) {
        Persistencia.guardaRegistroLog(tipoUsuario, nivel, accion,interfaz);
    }

    public List<ProductoDto> obtenerProductos(boolean f) {

        if(f){
            return mapper.getProductosDto(subastaUq.getListaproductos(nombreAnunciante));
        }else{
            return mapper.getProductosDto(subastaUq.getListaproductos());
        }
    }
    public List<ProductoDto> obtenerProductosAdquiridos(boolean f) {

        if(f){
            return mapper.getProductosDto(subastaUq.getListaProductosAdquiridos());
        }else{
            return mapper.getProductosDto(subastaUq.getListaProductosAdquiridos());
        }
    }
    public void agregarDatos ()
    {
       Producto producto = new Producto();
       producto.setNombreProducto("VENENO AZUL");
       producto.setFechaAdquirido("9/11/2023");
       producto.setTipoProducto("Vehiculo");

        subastaUq.getListaProductosAdquiridos().add(producto);
    }




    public boolean eliminarProducto(String nombre) {
        boolean flag = false;
        try {
            flag = subastaUq.eliminarProducto(nombre);
            guardarResourceXML();
            cargarResourceXML();
            registrarAccionesSistema("Anunciante", 2, "elimino a un producto debido a su compra o retiro por parte del propietario","publicaciones");
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
                String msg=producto.getNombreProducto()+"@"+producto.getTipoProducto()+"@"+producto.getDescProducto()+"@"+producto.getAnunciante()+"@"+producto.getValorInicial()
                        +"@"+producto.getFechaPublicacion()+"@"+producto.getFechaTerminarPublicacion()+"@"+producto.getFechaAdquirido();
                producirProductos(msg);
                centinela=true;
                guardarResourceXML();
                cargarResourceXML();
                registrarAccionesSistema("Anunciante",1,"agrego un Producto","CrearAnuncio");
            } catch (ProductoException e) {
                throw new RuntimeException(e);
            }


        }
      return centinela;
    }

   public List<PujaDto> obtenerProductosPuja(boolean flag){
        if(flag){
            return mapper.getPujasDto(subastaUq.getListaProductosPuja(nombreProducto,nombreAnunciante));
        }else{
            return mapper.getPujasDto(subastaUq.getListaProductosPuja(nombreComprador));
        }
   }

   public boolean eliminarPuja(String nombre){
        boolean flag=false;
        try{
            flag= subastaUq.eliminarPuja(nombre);
            guardarResourceXML();
            registrarAccionesSistema("Comprador",2,"Elimino una puja","pujas");
        }catch(Exception e){
            e.printStackTrace();
        }
   return flag;}

    public boolean agregarPuja(PujaDto pujaDto){
        try{
            if(!subastaUq.verificarCantidadPujas(pujaDto.nombreAnunciante())) {
                Puja puja = mapper.PujaDtoToPuja(pujaDto);
                subastaUq.agregarPuja(puja);
                registrarAccionesSistema("Comprador", 1, "realizo una puja","pujas");
            }
                return true;
            }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



}


