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

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tipoProducto")
public class TipoProductoController {


    @Autowired
    TipoProductoService tipoProductoService;

    /**
     * Este metodo retorna todas las categorias de productos que se encuentren registraaas
     * @return
     */
    @GetMapping("/TipoProducto")
    public List<TipoProducto> list(){
        List<TipoProducto> list = tipoProductoService.list();
        System.out.println("esta es la lista de tipo de productos");
        return list;
    }

    /**
     * Este metodo retorna una categoria de produco especifica
     * usando el id que recibe como parametro para realizar la consulta
     * @param id
     * @return
     */
    @GetMapping("/TipoProducto/{id}")
    public TipoProducto getById(@PathVariable("id") int id){
        if(!tipoProductoService.existsById(id))
            return null;
        TipoProducto tipoProducto = tipoProductoService.getOne(id).get();
        System.out.println("lo encontre");
        return tipoProducto;
    }

    /**
     * Este metodo crea nuevas categorias en la base de datos, recibe como parametro un objeto
     * (TipoProducto) el cual es guardado
     * @param tipoProducto
     * @return
     */
    @PostMapping("/createTipoProducto")
    public ResponseEntity<?> create(@RequestBody TipoProducto tipoProducto){
        if (tipoProducto.getNombre_tipo()==null)
            return new ResponseEntity(new Mensaje("escriba el nombre"), HttpStatus.BAD_REQUEST);

        TipoProducto tipoProductoo = new TipoProducto(tipoProducto.getNombre_tipo(), tipoProducto.getDescripcion());
        tipoProductoService.save(tipoProducto);
        System.out.println("se creo el tipoProducto");
        return new ResponseEntity(new Mensaje("tipoProducto creado"), HttpStatus.OK);
    }

    /**
     * Este metodo se encarga de modificar el nombre y la descripcion de una categoria en especifico
     * mediante el parametro id se indica cual sera la categoria a modificar y mediante el parametro
     * tipoProducto se indican los nuevos valores que tomara
     * @param id
     * @param tipoProducto
     * @return
     */
    @PutMapping("/updateTipoProducto/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody TipoProducto tipoProducto){
        if(!tipoProductoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        if (tipoProducto.getNombre_tipo()==null)
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);


        TipoProducto tipoProducto1 = tipoProductoService.getOne(id).get();

        tipoProducto1.setNombre_tipo(tipoProducto.getNombre_tipo());
        tipoProducto1.setDescripcion(tipoProducto.getDescripcion());


        tipoProductoService.save(tipoProducto1);
        System.out.println("se actualizo yeiii");
        return new ResponseEntity(new Mensaje("tipoProducto actualizado"), HttpStatus.OK);
    }

    /**
     * Este metodo se encarga de eliminar una categoria especifica,
     * recibe como parametro el id de la categoria que se desea borrar
     * @param id
     * @return
     */
    @DeleteMapping("/deleteTipoProducto/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!tipoProductoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        tipoProductoService.delete(id);
        return new ResponseEntity(new Mensaje("TipoProducto eliminado"), HttpStatus.OK);
    }





}
