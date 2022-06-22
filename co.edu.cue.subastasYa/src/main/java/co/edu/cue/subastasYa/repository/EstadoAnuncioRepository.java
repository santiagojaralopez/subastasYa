package co.edu.cue.subastasYa.repository;

import co.edu.cue.subastasYa.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstadoAnuncioRepository extends JpaRepository<Estado, Integer> {

    @Query("SELECT a FROM Estado a WHERE a.nombre='ACTIVO'")
    Estado getEstadoActivo();

    @Query("SELECT a FROM Estado a WHERE a.nombre='INACTIVO'")
    Estado getEstadoInactivo();

    @Query("SELECT a FROM Estado a WHERE a.nombre='BLOQUEADO'")
    Estado getEstadoBloqueado();

    @Query("SELECT a FROM Estado a WHERE a.nombre='VENDIDO'")
    Estado getEstadoVendido();
}
