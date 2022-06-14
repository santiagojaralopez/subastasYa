package co.edu.cue.subastasYa.controller;


import co.edu.cue.subastasYa.dto.AnuncioDto;
import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.dto.ProductoDto;
import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Estado;
import co.edu.cue.subastasYa.entity.Producto;
import co.edu.cue.subastasYa.security.entity.Usuario;
import co.edu.cue.subastasYa.service.AnuncioService;
import co.edu.cue.subastasYa.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    AnuncioService anuncioService;

    @GetMapping("/listaAnuncio")
    public List<Anuncio> list(){
        List<Anuncio> list = anuncioService.list();
        return list;
    }


    @GetMapping("/listaAnunciosUser")
    public List<Anuncio> listAnuncioUser(@RequestBody AnuncioDto anuncioDto){
        List<Anuncio> list = anuncioService.findAnunciosByUsuario(anuncioDto.getUsuario());
        System.out.println("mirame, soy los anuncios del usuario");
        return list;
    }


    //MOSTRAR ANUNCIOS ESTADOS
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listaAnuncioBloqueados")
    public List<Anuncio> listBloqueados(){
        List<Anuncio> list = anuncioService.listByEstados(Estado.BLOQUEADO);
        return list;
    }


    @GetMapping("/listaAnuncioActivos")
    public List<Anuncio> listActivos(){
        List<Anuncio> list = anuncioService.listByEstados(Estado.ACTIVO);
        return list;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listaAnuncioInactivo")
    public List<Anuncio> listInactivo(){
        List<Anuncio> list = anuncioService.listByEstados(Estado.INACTIVO);
        return list;
    }



    @GetMapping("/detailAnuncio/{id}")
    public Anuncio getById(@PathVariable("id") int id){
        if(!anuncioService.existsById(id))
            return null;
        Anuncio anuncio = anuncioService.getOne(id).get();
        return anuncio;
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/createAnuncio")
    public ResponseEntity<?> create(@RequestBody AnuncioDto anuncioDto){

        if (listAnuncioUser(anuncioDto).size() < anuncioService.cantidadAnuncios()) {
            System.out.println(anuncioDto.getUsuario().toString());
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
            if (anuncioDto.getDepartamento()==null)
                return new ResponseEntity(new Mensaje("el departamento es obligatorio"), HttpStatus.BAD_REQUEST);
            if (anuncioDto.getProducto()==null)
                return new ResponseEntity(new Mensaje("el producto es obligatorio"), HttpStatus.BAD_REQUEST);

             int dias= anuncioService.diasAnuncioActivo();

            Date dt = anuncioDto.getFecha_inicio();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, dias);
            dt = c.getTime();


            Anuncio anuncio = new Anuncio(anuncioDto.getDescripcion(), anuncioDto.getFecha_inicio(), dt, anuncioDto.getUsuario(), anuncioDto.getEstado(), anuncioDto.getCiudad(), anuncioDto.getDepartamento(), anuncioDto.getValor(), anuncioDto.getProducto());
            anuncioService.save(anuncio);
            return new ResponseEntity(new Mensaje("anuncio creado"), HttpStatus.OK);
         } else
          return new ResponseEntity(new Mensaje("La cantidad maxima de anuncios fue alcanzada, no puede crear mas"), HttpStatus.BAD_REQUEST);
    }




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
        if (anuncioDto.getDepartamento()==null)
            return new ResponseEntity(new Mensaje("el departamento es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncioDto.getProducto()==null)
            return new ResponseEntity(new Mensaje("el producto es obligatorio"), HttpStatus.BAD_REQUEST);

        Anuncio anuncio = anuncioService.getOne(id).get();

        anuncio.setDescripcion(anuncioDto.getDescripcion());
        anuncio.setFecha_inicio(anuncioDto.getFecha_inicio());
        anuncio.setFecha_fin(anuncioDto.getFecha_fin());
        anuncio.setUsuario(anuncioDto.getUsuario());
        anuncio.setEstado(anuncioDto.getEstado());
        anuncio.setCiudad(anuncioDto.getCiudad());
        anuncio.setDepartamento(anuncioDto.getDepartamento());
        anuncio.setValor(anuncioDto.getValor());
        anuncio.setProducto(anuncioDto.getProducto());

        anuncioService.save(anuncio);
        System.out.println("se actualizo yeiii");
        return new ResponseEntity(new Mensaje("anuncio actualizado"), HttpStatus.OK);
    }


    @DeleteMapping("/deleteAnuncio/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        anuncioService.delete(id);
        return new ResponseEntity(new Mensaje("anuncio eliminado"), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateAnuncioBloqueo/{id}")
    public ResponseEntity<?> updateBloqueoAdmin(@PathVariable("id")int id, @RequestBody AnuncioDto anuncioDto) {
        if (!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncioDto.getEstado()==Estado.ACTIVO || anuncioDto.getEstado()==Estado.INACTIVO) {
            Anuncio anuncio = anuncioService.getOne(id).get();
            anuncio.setEstado(Estado.BLOQUEADO);
            anuncioService.save(anuncio);
            System.out.println("estado BLOQUEADO jiji");
            return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
        } else return new ResponseEntity(new Mensaje("el estado es obligatorio"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateAnuncioActivar/{id}")
    public ResponseEntity<?> updateActivarAdmiUser(@PathVariable("id")int id, @RequestBody AnuncioDto anuncioDto) {
        if (!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncioDto.getEstado()==Estado.BLOQUEADO || anuncioDto.getEstado()==Estado.INACTIVO) {
            Anuncio anuncio = anuncioService.getOne(id).get();
            anuncio.setEstado(Estado.ACTIVO);
            anuncioService.save(anuncio);
            System.out.println("estado ACTIVO jiji");
            return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
        } else return new ResponseEntity(new Mensaje("el estado es obligatorio"), HttpStatus.BAD_REQUEST);

    }


    @PutMapping("/updateAnuncioInactivo/{id}")
    public ResponseEntity<?> updateInactivoUser(@PathVariable("id")int id, @RequestBody AnuncioDto anuncioDto) {
        if (!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncioDto.getEstado()==Estado.ACTIVO || anuncioDto.getEstado()==Estado.BLOQUEADO) {
            Anuncio anuncio = anuncioService.getOne(id).get();
            anuncio.setEstado(Estado.INACTIVO);
            anuncioService.save(anuncio);
            System.out.println("estado INACTIVO jiji");
            return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
        } else return new ResponseEntity(new Mensaje("el estado es obligatorio"), HttpStatus.BAD_REQUEST);
    }


    //TIPO DE PRODUCTOS SOLO DE LA LISTA

}
