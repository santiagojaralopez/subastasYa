package co.edu.cue.subastasYa.repository;

import co.edu.cue.subastasYa.entity.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionRepository extends JpaRepository<Configuracion, Integer> {
    @Query("SELECT c.valor FROM Configuracion c WHERE c.nombre= 'maximo_ofertas'")
    int cantidadMaximaOfertas();

    @Query("SELECT c.valor FROM Configuracion c WHERE c.nombre ='edad_registro'")
    int edadMinimaRegistro();
}
