package co.edu.cue.subastasYa.controller;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.TipoProducto;
import co.edu.cue.subastasYa.service.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tipoProducto")
public class TipoProductoController {

    @Autowired
    TipoProductoService tipoProductoService;

    @GetMapping("/lista")
    public List<TipoProducto> list(){
        List<TipoProducto> list = tipoProductoService.list();
        return list;
    }
}
