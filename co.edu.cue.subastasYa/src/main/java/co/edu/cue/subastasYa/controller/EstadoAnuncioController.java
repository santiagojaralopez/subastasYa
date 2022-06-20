package co.edu.cue.subastasYa.controller;

import co.edu.cue.subastasYa.enums.Estado;
import co.edu.cue.subastasYa.service.EstadoAnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/estado")
public class EstadoAnuncioController {

    @Autowired
    EstadoAnuncioService estadoAnuncioService;

    @GetMapping("/get-activo")
    public Estado getEstadoActivo(){
        Estado estado = estadoAnuncioService.getEstadoActivo();
        return estado;
    }

    @GetMapping("/get-inactivo")
    public Estado getEstadoInactivo(){
        Estado estado = estadoAnuncioService.getEstadoInactivo();
        return estado;
    }

    @GetMapping("/get-bloqueado")
    public Estado getEstadoBloqueado(){
        Estado estado = estadoAnuncioService.getEstadoBloqueado();
        return estado;
    }
}
