package co.edu.cue.subastasYa.service;


import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Estado;
import co.edu.cue.subastasYa.entity.TipoProducto;
import co.edu.cue.subastasYa.repository.AnuncioRepository;
import co.edu.cue.subastasYa.repository.TipoProductoRepository;
import co.edu.cue.subastasYa.security.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipoProductoService {


    @Autowired
    TipoProductoRepository tipoProductoRepository;

    public List<TipoProducto> list(){ return tipoProductoRepository.findAll();}


   public Optional<TipoProducto> getOne(int id){return tipoProductoRepository.findById(id);}

    public void  save(TipoProducto tipoProducto){ tipoProductoRepository.save(tipoProducto);}

    public void delete(int id){ tipoProductoRepository.deleteById(id);}

    public boolean existsById(int id){return tipoProductoRepository.existsById(id);}

    public Optional<TipoProducto> findTipoProductoByNombre(String nombre) {
        return tipoProductoRepository.findTipoProductoByNombre(nombre);
    }



}
