package co.edu.cue.subastasYa.controller;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.enums.Ciudad;
import co.edu.cue.subastasYa.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    CiudadService ciudadService;

    /**
     * Este metodo retorna la lista de ciudades
     * @return
     */
    @GetMapping("/listaCiudades")
    public List<Ciudad> list(){
        List<Ciudad> list = ciudadService.list();
        return list;
    }
}
