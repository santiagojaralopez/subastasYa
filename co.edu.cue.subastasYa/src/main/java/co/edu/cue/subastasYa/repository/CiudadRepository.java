package co.edu.cue.subastasYa.repository;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Ciudad;
import co.edu.cue.subastasYa.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

    @Query("SELECT a FROM Ciudad a WHERE a.usuario= :usuario")
    Optional<Anuncio> findAnuncioByUsuario(
            @Param("usuario") Usuario usuario
    );
}
