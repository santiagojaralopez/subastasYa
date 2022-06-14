package co.edu.cue.subastasYa.repository;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Estado;
import co.edu.cue.subastasYa.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{


    @Query("SELECT a FROM Anuncio a WHERE a.estado= :estado")
    List<Anuncio> findAnuncioByEstado(
            @Param("estado") Estado estado);


    @Query("SELECT c.valor FROM Configuracion c WHERE c.nombre= 'cantidadAnunciosActivos'")
    int cantidadAnuncios();


    @Query("SELECT c.valor FROM Configuracion c WHERE c.nombre= 'diasAnunciosActivos'")
    int diasAnuncioActivo();



    @Query("SELECT a FROM Anuncio a WHERE a.usuario= :usuario")
    Optional<Anuncio> findAnuncioByUsuario(
            @Param("usuario") Usuario usuario);



    @Query("SELECT a FROM Anuncio a WHERE a.usuario= :usuario")
    List<Anuncio> findListaAnunciosByUsuario(
            @Param("usuario") Usuario usuario);


}
