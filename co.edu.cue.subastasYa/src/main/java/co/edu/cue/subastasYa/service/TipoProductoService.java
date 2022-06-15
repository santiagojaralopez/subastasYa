package co.edu.cue.subastasYa.service;

import co.edu.cue.subastasYa.entity.TipoProducto;
import co.edu.cue.subastasYa.repository.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TipoProductoService {

    @Autowired
    TipoProductoRepository tipoProductoRepository;


    public TipoProducto showTypeExist(String nombreTipo){
        return tipoProductoRepository.findListaTypesProduct(nombreTipo);
    }



}
