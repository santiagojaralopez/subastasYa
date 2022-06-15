package co.edu.cue.subastasYa.security.repository;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Estado;
import co.edu.cue.subastasYa.security.entity.Usuario;
import co.edu.cue.subastasYa.security.enums.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByNombreUsuarioOrEmail(String nombreUsuario, String email);
    Optional<Usuario> findByTokenPassword(String tokenPassword);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.estadoUsuario= :estadoUsuario")
    List<Usuario> findUsuarioByEstado(
            @Param("estadoUsuario") EstadoUsuario estadoUsuario
    );



}
