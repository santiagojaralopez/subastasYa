package co.edu.cue.subastasYa.repository;

import co.edu.cue.subastasYa.entity.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer>{

}
