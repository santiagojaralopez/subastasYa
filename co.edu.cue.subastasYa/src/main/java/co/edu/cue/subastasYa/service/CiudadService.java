package co.edu.cue.subastasYa.service;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Ciudad;
import co.edu.cue.subastasYa.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CiudadService {

    @Autowired
    CiudadRepository ciudadRepository;

    public List<Ciudad> list(){
        return ciudadRepository.findAll();
    }



}
