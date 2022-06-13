package co.edu.cue.subastasYa.service;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Estado;
import co.edu.cue.subastasYa.entity.Producto;
import co.edu.cue.subastasYa.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnuncioService {
    @Autowired
    AnuncioRepository anuncioRepository;


    public List<Anuncio> list(){
        return anuncioRepository.findAll();
    }



    public List<Anuncio> listByEstados(Estado estado) {

        if (estado == Estado.BLOQUEADO)
            return anuncioRepository.findAnuncioByEstado(Estado.BLOQUEADO);
        if (estado == Estado.ACTIVO)
            return anuncioRepository.findAnuncioByEstado(Estado.ACTIVO);
        return anuncioRepository.findAnuncioByEstado(Estado.INACTIVO);
    }




    public Optional<Anuncio> getOne(int id){
        return anuncioRepository.findById(id);
    }

    public void  save(Anuncio anuncio){
        anuncioRepository.save(anuncio);
    }

    public void delete(int id){
        anuncioRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return anuncioRepository.existsById(id);
    }



}
