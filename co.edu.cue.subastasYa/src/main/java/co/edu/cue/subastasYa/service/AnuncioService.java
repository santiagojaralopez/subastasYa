package co.edu.cue.subastasYa.service;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.TipoProducto;
import co.edu.cue.subastasYa.repository.AnuncioRepository;
import co.edu.cue.subastasYa.security.entity.Usuario;
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

    public List<Anuncio> listByEstadosBloqueado() {
            return anuncioRepository.findAnunciosByBloqueado();
    }

    public List<Anuncio> listByEstadosActivo() {
        return anuncioRepository.findAnunciosByActivo();
    }

    public List<Anuncio> listByEstadosInactivo() {
        return anuncioRepository.findAnunciosByInactivo();
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


    public Optional<Anuncio> findAnuncioByUsuario(Usuario usuario) {
        return anuncioRepository.findAnuncioByUsuario(usuario);
    }

    public List<Anuncio> findAnunciosByUsuario(String username){
        return anuncioRepository.findListaAnunciosByUsuario(username);
    }


    public int cantidadAnuncios(){
       return anuncioRepository.cantidadAnuncios();
    }


    public int diasAnuncioActivo(){
        return anuncioRepository.diasAnuncioActivo();
    }


}
