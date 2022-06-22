package co.edu.cue.subastasYa.service;

import co.edu.cue.subastasYa.entity.Configuracion;
import co.edu.cue.subastasYa.enums.Estado;
import co.edu.cue.subastasYa.repository.ConfiguracionRepository;
import co.edu.cue.subastasYa.repository.EstadoAnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EstadoAnuncioService {

    @Autowired
    EstadoAnuncioRepository estadoAnuncioRepository;

    public Estado getEstadoActivo(){
        return estadoAnuncioRepository.getEstadoActivo();
    }

    public Estado getEstadoInactivo(){
        return estadoAnuncioRepository.getEstadoInactivo();
    }

    public Estado getEstadoBloqueado(){
        return estadoAnuncioRepository.getEstadoBloqueado();
    }

    public Estado getEstadoVendido(){
        return estadoAnuncioRepository.getEstadoVendido();
    }

}
