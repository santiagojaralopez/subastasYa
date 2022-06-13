package co.edu.cue.subastasYa.repository;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{

    @Query("SELECT a FROM Anuncio a WHERE a.estado= :estado")
    List<Anuncio> findAnuncioByEstado(
            @Param("estado") Estado estado);


    @Query("SELECT c FROM Configuracion c WHERE c.nombre_configuracion= cantidadAnunciosActivos")
    int findCantidadAnuncios();


    @Query("SELECT c FROM Configuracion c WHERE c.nombre_configuracion=cantidadDiasActivo")
    int findDiasAnuncioActivo();



}
