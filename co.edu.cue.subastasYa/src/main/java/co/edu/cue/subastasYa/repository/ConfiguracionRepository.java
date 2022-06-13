package co.edu.cue.subastasYa.repository;


import co.edu.cue.subastasYa.entity.Configuracion;
import co.edu.cue.subastasYa.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracionRepository extends JpaRepository<Configuracion, Integer> {








}
