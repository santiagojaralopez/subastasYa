package co.edu.cue.subastasYa.service;

import co.edu.cue.subastasYa.entity.Configuracion;
import co.edu.cue.subastasYa.repository.ConfiguracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ConfiguracionService {

    @Autowired
    ConfiguracionRepository configuracionRepository;

    public void  save(Configuracion configuracion){
        configuracionRepository.save(configuracion);
    }

    public boolean existsById(int id) {
        return configuracionRepository.existsById(id);
    }

    public Optional<Configuracion> getById(int id) {return configuracionRepository.findById(id); }

    public int cantidadMaximaOfertas() {
        return configuracionRepository.cantidadMaximaOfertas();
    }

    public int edadMinimaRegistro() {
        return configuracionRepository.edadMinimaRegistro();
    }
}
