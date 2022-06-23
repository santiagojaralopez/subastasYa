package co.edu.cue.subastasYa.controller;


import co.edu.cue.subastasYa.dto.AnuncioDto;
import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.TipoProducto;
import co.edu.cue.subastasYa.entity.Producto;
import co.edu.cue.subastasYa.service.AnuncioService;
import co.edu.cue.subastasYa.service.EstadoAnuncioService;
import co.edu.cue.subastasYa.service.ProductoService;
import co.edu.cue.subastasYa.service.TipoProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    AnuncioService anuncioService;

    @Autowired
    TipoProductoService tipoProductoService;

    @Autowired
    ProductoService productoService;

    @Autowired
    EstadoAnuncioService estadoAnuncioService;

    @GetMapping("/listaAnuncio")
    public List<Anuncio> list(){
        List<Anuncio> list = anuncioService.list();
        return list;
    }

    @GetMapping("/listaAnunciosUser/{username}")
    public List<Anuncio> listAnuncioUser(@PathVariable("username") String username) {
        System.out.println("Usuariooooooo: " + username);
        List<Anuncio> list = anuncioService.findAnunciosByUsuario(username);
        System.out.println("mirame, soy los anuncios del usuario");
        return list;
    }

    //MOSTRAR ANUNCIOS ESTADOS
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listaAnuncioBloqueados")
    public List<Anuncio> listBloqueados(){
        List<Anuncio> list = anuncioService.listByEstadosBloqueado();
        if (list!=null){
            return list;
        } else
            return null;
    }

    @GetMapping("/listaAnuncioActivos")
    public List<Anuncio> listActivos(){
        List<Anuncio> list = anuncioService.listByEstadosActivo();
        if (list!=null){
            return list;
        } else
            return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listaAnuncioInactivo")
    public List<Anuncio> listInactivos(){
        List<Anuncio> list = anuncioService.listByEstadosInactivo();
        if (list!=null){
            return list;
        } else
            return null;
    }

    @GetMapping("/detailAnuncio/{id}")
    public Anuncio getById(@PathVariable("id") int id){
        if(!anuncioService.existsById(id))
            return null;
        Anuncio anuncio = anuncioService.getOne(id).get();
        return anuncio;
    }

    /**
     *Descripcion: este metodo crea un nuevo anuncio, validando que los campos esten completos, creando un nuevo producto,
     * buscando si el tipo de producto existe, y validando que la fecha de fin de el anuncio cumpla con la cantidad de dias estipulados
     * @param anuncioDto
     * @return ResponseEntity con HttpStatus
     */
    @PostMapping("/createAnuncio")
    public ResponseEntity<?> create(@RequestBody AnuncioDto anuncioDto){
        Producto producto = new Producto(anuncioDto.getProducto().getNombre(),anuncioDto.getProducto().getFoto_producto(),anuncioDto.getProducto().getTipoproducto());
        productoService.save(producto);

        if (listAnuncioUser(anuncioDto.getUsuario().getNombreUsuario()).size() < anuncioService.cantidadAnuncios()) {
            System.out.println(anuncioDto.getUsuario().toString());

            if(StringUtils.isBlank(anuncioDto.getDescripcion()))
                return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
            if (anuncioDto.getValor()==0)
                return new ResponseEntity(new Mensaje("el precio es obligatorio y debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
            if (anuncioDto.getCiudad()==null)
                return new ResponseEntity(new Mensaje("la ciudad es obligatoria"), HttpStatus.BAD_REQUEST);
            if (anuncioDto.getProducto()==null)
                return new ResponseEntity(new Mensaje("el producto es obligatorio"), HttpStatus.BAD_REQUEST);

            //calculo de fecha actual
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            int dias= anuncioService.diasAnuncioActivo();
            Date dt = date;
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, dias);
            dt = c.getTime();

            //TIPOS DE PRODUCTO
            TipoProducto existeTipoProducto = tipoProducto(anuncioDto);

            if (existeTipoProducto!=null){
                Anuncio anuncio = new Anuncio(anuncioDto.getDescripcion(), date, dt, anuncioDto.getUsuario(),estadoAnuncioService.getEstadoActivo(), anuncioDto.getCiudad(), anuncioDto.getValor(), producto);
                anuncioService.save(anuncio);
                return new ResponseEntity(new Mensaje("anuncio creado"), HttpStatus.OK);
              } else
                  return new ResponseEntity(new Mensaje("El tipo de producto ingresado no existe"), HttpStatus.BAD_REQUEST);
        } else
            return new ResponseEntity(new Mensaje("La cantidad maxima de anuncios fue alcanzada, no puede crear mas"), HttpStatus.BAD_REQUEST);
    }


    public TipoProducto tipoProducto(AnuncioDto anuncioDto){
        return tipoProductoService.showTypeExist(anuncioDto.getProducto().getTipoproducto().getNombre_tipo());
    }


    /**
     * Descripcion: este metodo recibe un anuncio y id. Busca el anuncio con ese id y lo actualiza comprobando que los campos estén completos
     * @param id
     * @param anuncioDto
     * @return ResponseEntity con HttpStatus
     */
    @PutMapping("/updateAnuncio/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody AnuncioDto anuncioDto){
        if(!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);


        if(StringUtils.isBlank(anuncioDto.getDescripcion()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncioDto.getValor()==0)
            return new ResponseEntity(new Mensaje("el precio es obligatorio y debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        if (anuncioDto.getUsuario()==null)
            return new ResponseEntity(new Mensaje("el usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncioDto.getEstado()==null)
            return new ResponseEntity(new Mensaje("el estado es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncioDto.getCiudad()==null)
            return new ResponseEntity(new Mensaje("la ciudad es obligatorio"), HttpStatus.BAD_REQUEST);


        Anuncio anuncio = anuncioService.getOne(id).get();

        anuncio.setDescripcion(anuncioDto.getDescripcion());
        anuncio.setFecha_inicio(anuncioDto.getFecha_inicio());
        anuncio.setFecha_fin(anuncioDto.getFecha_fin());
        anuncio.setUsuario(anuncioDto.getUsuario());
        anuncio.setEstado(anuncioDto.getEstado());
        anuncio.setCiudad(anuncioDto.getCiudad());
        anuncio.setValor(anuncioDto.getValor());
        anuncio.setProducto(anuncioDto.getProducto());

        anuncioService.save(anuncio);
        return new ResponseEntity(new Mensaje("anuncio actualizado"), HttpStatus.OK);
    }

    /**
     * Descripcion: este metodo tiene la funcionalidad de borrar un anuncio segun el id del anuncio
     * @param id
     * @return ResponseEntity con HttpStatus
     */
    @DeleteMapping("/deleteAnuncio/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        anuncioService.delete(id);
        return new ResponseEntity(new Mensaje("anuncio eliminado"), HttpStatus.OK);
    }


    /**
     * Descripcion: este metodo bloquea (cambia el estado) un anuncio solo si el usuairo registrado es el administrador de la página
     * @param id
     * @param anuncioDto
     * @return ResponseEntity con HttpStatus
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateAnuncioBloqueo/{id}")
    public ResponseEntity<?> updateBloqueoAdmin(@PathVariable("id")int id, @RequestBody AnuncioDto anuncioDto) {
        if (!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncioDto.getEstado().getId_estado()==2 || anuncioDto.getEstado().getId_estado()==3) {
            Anuncio anuncio = anuncioService.getOne(id).get();
            anuncio.setEstado(estadoAnuncioService.getEstadoBloqueado());
            anuncioService.save(anuncio);
            System.out.println("estado BLOQUEADO jiji");
            return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
        } else return new ResponseEntity(new Mensaje("el anuncio no se puede bloquear"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Descripcion: este metodo activa (cambia el estado) un anuncio ya sea un usuario o el administrador
     * @param id
     * @param anuncioDto
     * @return ResponseEntity con HttpStatus
     */
    @PutMapping("/updateAnuncioActivar/{id}")
    public ResponseEntity<?> updateActivarAdmiUser(@PathVariable("id")int id, @RequestBody AnuncioDto anuncioDto) {
        if (!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncioDto.getEstado().getId_estado()==1  || anuncioDto.getEstado().getId_estado()==3) {
            Anuncio anuncio = anuncioService.getOne(id).get();
            anuncio.setEstado(estadoAnuncioService.getEstadoActivo());
            anuncioService.save(anuncio);
            return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
        } else return new ResponseEntity(new Mensaje("el anuncio no se puede activar"), HttpStatus.BAD_REQUEST);

    }

    /**
     * Descripcion: este metodo inactiva (cambia el estado) un anuncio ya sea un usuario o el administrador
     * @param id
     * @param anuncioDto
     * @return ResponseEntity con HttpStatus
     */
    @PutMapping("/updateAnuncioInactivo/{id}")
    public ResponseEntity<?> updateInactivoUser(@PathVariable("id")int id, @RequestBody AnuncioDto anuncioDto) {
        if (!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncioDto.getEstado().getId_estado()==2 || anuncioDto.getEstado().getId_estado()==1) {
            Anuncio anuncio = anuncioService.getOne(id).get();
            anuncio.setEstado(estadoAnuncioService.getEstadoInactivo());
            anuncioService.save(anuncio);
            return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
        } else return new ResponseEntity(new Mensaje("el anuncio no se puede desactivar"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Descripcion: este metodo vende (cambia el estado) un anuncio solo si el usuario es usuario
     * @param id
     * @param anuncioDto
     * @return  ResponseEntity con HttpStatus
     */
    @PutMapping("/updateAnuncioVendido/{id}")
    public ResponseEntity<?> updateVendidoUser(@PathVariable("id")int id, @RequestBody AnuncioDto anuncioDto) {
        if (!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncioDto.getEstado().getId_estado()==2) {
            Anuncio anuncio = anuncioService.getOne(id).get();
            anuncio.setEstado(estadoAnuncioService.getEstadoVendido());
            anuncioService.save(anuncio);
            return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
        } else return new ResponseEntity(new Mensaje("el anuncio no se puede vender"), HttpStatus.BAD_REQUEST);
    }


}
