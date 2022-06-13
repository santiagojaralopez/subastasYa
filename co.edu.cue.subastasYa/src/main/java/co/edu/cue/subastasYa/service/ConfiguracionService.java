package co.edu.cue.subastasYa.service;

import co.edu.cue.subastasYa.entity.Configuracion;
import co.edu.cue.subastasYa.entity.Producto;
import co.edu.cue.subastasYa.repository.ConfiguracionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfiguracionService {

    @Autowired
    ConfiguracionRepository configuracionRepository;


        public void  save(Configuracion configuracion){
            configuracionRepository.save(configuracion);
        }

    public boolean existsById(int id) {
        return configuracionRepository.existsById(id);
    }
}


