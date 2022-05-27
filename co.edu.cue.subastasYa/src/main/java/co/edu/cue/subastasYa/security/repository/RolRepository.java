package co.edu.cue.subastasYa.security.repository;

import co.edu.cue.subastasYa.security.entity.Rol;
import co.edu.cue.subastasYa.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
