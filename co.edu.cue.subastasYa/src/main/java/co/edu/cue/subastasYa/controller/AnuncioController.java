package co.edu.cue.subastasYa.controller;


import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.dto.ProductoDto;
import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Estado;
import co.edu.cue.subastasYa.entity.Producto;
import co.edu.cue.subastasYa.service.AnuncioService;
import co.edu.cue.subastasYa.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("mirame, soy los anuncios");
        return list;
    }


    
    //MOSTRAR ANUNCIOS ESTADOS
    @GetMapping("/listaAnuncioBloqueados")
    public List<Anuncio> listBloqueados(){
        return anuncioService.listByEstados(Estado.BLOQUEADO);
    }
    @GetMapping("/listaAnuncioActivos")
    public List<Anuncio> listActivos(){
        return anuncioService.listByEstados(Estado.ACTIVO);
    }
    @GetMapping("/listaAnuncioInactivo")
    public List<Anuncio> listInactivo(){
        return anuncioService.listByEstados(Estado.INACTIVO);
    }





    @GetMapping("/detailAnuncio/{id}")
    public Anuncio getById(@PathVariable("id") int id){
        if(!anuncioService.existsById(id))
            return null;
        Anuncio anuncio = anuncioService.getOne(id).get();
        System.out.println("lo encontre");
        return anuncio;
    }


    @PostMapping("/createAnuncio")
    public ResponseEntity<?> create(@RequestBody Anuncio anuncio){
        if(StringUtils.isBlank(anuncio.getDescripcion()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getValor()==0)
            return new ResponseEntity(new Mensaje("el precio es obligatorio y debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        if (anuncio.getUsuario()==null)
            return new ResponseEntity(new Mensaje("el usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getEstado()==null)
            return new ResponseEntity(new Mensaje("el estado es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getCiudad()==null)
            return new ResponseEntity(new Mensaje("la ciudad es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getDepartamento()==null)
            return new ResponseEntity(new Mensaje("el departamento es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getProducto()==null)
            return new ResponseEntity(new Mensaje("el producto es obligatorio"), HttpStatus.BAD_REQUEST);

        Anuncio anuncio1 = new Anuncio(anuncio.getDescripcion(), anuncio.getFecha_inicio(), anuncio.getFecha_fin(), anuncio.getUsuario(), anuncio.getEstado(), anuncio.getCiudad(), anuncio.getDepartamento(), anuncio.getValor(), anuncio.getProducto());
        anuncioService.save(anuncio1);
        System.out.println("se creo wepaaaa");
        return new ResponseEntity(new Mensaje("anuncio creado"), HttpStatus.OK);
    }


    @PutMapping("/updateAnuncio/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Anuncio anuncio){
        if(!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(anuncio.getDescripcion()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getValor()==0)
            return new ResponseEntity(new Mensaje("el precio es obligatorio y debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        if (anuncio.getUsuario()==null)
            return new ResponseEntity(new Mensaje("el usuario es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getEstado()==null)
            return new ResponseEntity(new Mensaje("el estado es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getCiudad()==null)
            return new ResponseEntity(new Mensaje("la ciudad es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getDepartamento()==null)
            return new ResponseEntity(new Mensaje("el departamento es obligatorio"), HttpStatus.BAD_REQUEST);
        if (anuncio.getProducto()==null)
            return new ResponseEntity(new Mensaje("el producto es obligatorio"), HttpStatus.BAD_REQUEST);

        Anuncio anuncio2 = anuncioService.getOne(id).get();

        anuncio2.setDescripcion(anuncio.getDescripcion());
        anuncio2.setFecha_inicio(anuncio.getFecha_inicio());
        anuncio2.setFecha_fin(anuncio.getFecha_fin());
        anuncio2.setUsuario(anuncio.getUsuario());
        anuncio2.setEstado(anuncio.getEstado());
        anuncio2.setCiudad(anuncio.getCiudad());
        anuncio2.setDepartamento(anuncio.getDepartamento());
        anuncio2.setValor(anuncio.getValor());
        anuncio2.setProducto(anuncio.getProducto());

        anuncioService.save(anuncio2);
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


    @PutMapping("/updateAnuncioBloqueo/{id}")
    public ResponseEntity<?> updateBloqueoAdmin(@PathVariable("id")int id, @RequestBody Anuncio anuncio){
        if(!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncio.getEstado()==Estado.ACTIVO)
            return new ResponseEntity(new Mensaje("el estado es obligatorio"), HttpStatus.BAD_REQUEST);
        Anuncio anuncio2 = anuncioService.getOne(id).get();
        anuncio2.setEstado(Estado.BLOQUEADO);

        anuncioService.save(anuncio2);
        System.out.println("estado BLOQUEADO jiji");
        return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
    }

    @PutMapping("/updateAnuncioActivar/{id}")
    public ResponseEntity<?> updateActivarAdmiUser(@PathVariable("id")int id, @RequestBody Anuncio anuncio) {
        if (!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncio.getEstado()==Estado.BLOQUEADO || anuncio.getEstado()==Estado.INACTIVO)
            return new ResponseEntity(new Mensaje("el estado es obligatorio"), HttpStatus.BAD_REQUEST);
        Anuncio anuncio2 = anuncioService.getOne(id).get();
        anuncio2.setEstado(Estado.ACTIVO);
        anuncioService.save(anuncio2);
        System.out.println("estado ACTIVO jiji");
        return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
    }


    @PutMapping("/updateAnuncioInactivo/{id}")
    public ResponseEntity<?> updateInactivoUser(@PathVariable("id")int id, @RequestBody Anuncio anuncio){
        if(!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (anuncio.getEstado()==Estado.ACTIVO)
            return new ResponseEntity(new Mensaje("el estado es obligatorio"), HttpStatus.BAD_REQUEST);

        Anuncio anuncio2 = anuncioService.getOne(id).get();
        anuncio2.setEstado(Estado.INACTIVO);

        anuncioService.save(anuncio2);
        System.out.println("estado INACTIVO jiji");
        return new ResponseEntity(new Mensaje("estado del anuncio actualizado"), HttpStatus.OK);
    }




}
