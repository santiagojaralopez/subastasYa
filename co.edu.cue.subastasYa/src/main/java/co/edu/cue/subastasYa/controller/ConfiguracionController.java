package co.edu.cue.subastasYa.controller;


import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.dto.ProductoDto;
import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Configuracion;
import co.edu.cue.subastasYa.entity.Producto;
import co.edu.cue.subastasYa.repository.ConfiguracionRepository;
import co.edu.cue.subastasYa.service.ConfiguracionService;
import co.edu.cue.subastasYa.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/configuracion")
@CrossOrigin(origins = "http://localhost:4200")
public class ConfiguracionController {

    @Autowired
    ConfiguracionService configuracionService;

    /**
     * Este metodo actualiza el valor de una de las configuraciones parametrizables, indicando
     * cual sera la modificada mediante el id que recibe como parametro
     * @param id
     * @param configuracion
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-config/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Configuracion configuracion){
        if(!configuracionService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(configuracion.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(configuracion.getNombre()==null || configuracion.getValor()==0 )
            return new ResponseEntity(new Mensaje("el valo"), HttpStatus.BAD_REQUEST);

        Configuracion configuracion1 = configuracionService.getById(id).get();
        configuracion1.setNombre(configuracion.getNombre());
        configuracion1.setValor(configuracion.getValor());
        configuracionService.save(configuracion1);
        return new ResponseEntity(new Mensaje("la configuracion esta actualizada"), HttpStatus.OK);
    }

    /**
     * Este metodo retorna una configuracion en especifico, buscandola por su id
     * el cual recibe como parametro
     * @param id
     * @return
     */
    @GetMapping("/detailConfig/{id}")
    public Configuracion getById(@PathVariable("id") int id){
        if(!configuracionService.existsById(id))
            return null;
        Configuracion configuracion = configuracionService.getById(id).get();
        return configuracion;
    }


   }


