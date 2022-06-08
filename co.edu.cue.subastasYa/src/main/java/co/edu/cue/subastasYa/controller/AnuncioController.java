package co.edu.cue.subastasYa.controller;


import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Producto;
import co.edu.cue.subastasYa.service.AnuncioService;
import co.edu.cue.subastasYa.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anuncio")
@CrossOrigin(origins = "http://localhost:4200")
public class AnuncioController {

    @Autowired
    AnuncioService anuncioService;


    @GetMapping("/listaAnuncio")
    public ResponseEntity<List<Anuncio>> list(){
        List<Anuncio> list = anuncioService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }


    @GetMapping("/detailAnuncio/{id}")
    public ResponseEntity getById(@PathVariable("id") int id){
        if(!anuncioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Anuncio anuncio = anuncioService.getOne(id).get();
        return new ResponseEntity(anuncio, HttpStatus.OK);
    }


}
