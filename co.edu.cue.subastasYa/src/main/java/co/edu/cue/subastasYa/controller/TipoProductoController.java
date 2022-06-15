package co.edu.cue.subastasYa.controller;


import co.edu.cue.subastasYa.dto.AnuncioDto;
import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.TipoProducto;
import co.edu.cue.subastasYa.service.AnuncioService;
import co.edu.cue.subastasYa.service.TipoProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tipoProducto")
public class TipoProductoController {


    @Autowired
    TipoProductoService tipoProductoService;

    @GetMapping("/TipoProducto")
    public List<TipoProducto> list(){
        List<TipoProducto> list = tipoProductoService.list();
        System.out.println("esta es la lista de tipo de productos");
        return list;
    }

    @GetMapping("/TipoProducto/{id}")
    public TipoProducto getById(@PathVariable("id") int id){
        if(!tipoProductoService.existsById(id))
            return null;
        TipoProducto tipoProducto = tipoProductoService.getOne(id).get();
        System.out.println("lo encontre");
        return tipoProducto;
    }


    @PostMapping("/createTipoProducto")
    public ResponseEntity<?> create(@RequestBody TipoProducto tipoProducto){
        if(StringUtils.isBlank(tipoProducto.getId(int id);
            return new ResponseEntity(new Mensaje("escriba el id"), HttpStatus.BAD_REQUEST);

        if (tipoProducto.getNombreTipo()==null)
            return new ResponseEntity(new Mensaje("escriba el nombre"), HttpStatus.BAD_REQUEST);

        TipoProducto tipoProducto = new TipoProducto(tipoProducto.getId(), tipoProducto.getNombreTipo());
        tipoProductoService.save(tipoProducto);
        System.out.println("se creo el tipoProducto");
        return new ResponseEntity(new Mensaje("tipoProducto creado"), HttpStatus.OK);
    }

    @PutMapping("/updateTipoProducto/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody TipoProducto tipoProducto){
        if(!tipoProductoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        if (tipoProducto.getNombreTipo()==null)
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);


        TipoProducto tipoProducto1 = tipoProductoService.getOne(id).get();

        tipoProducto.setId(int id);

        tipoProducto.setNombreTipo(String nombre);


        tipoProductoService.save(tipoProducto);
        System.out.println("se actualizo yeiii");
        return new ResponseEntity(new Mensaje("tipoProducto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTipoProducto/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!tipoProductoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        tipoProductoService.delete(id);
        return new ResponseEntity(new Mensaje("TipoProducto eliminado"), HttpStatus.OK);
    }





}
