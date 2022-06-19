package co.edu.cue.subastasYa.repository;

import co.edu.cue.subastasYa.entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {
    @Query("SELECT t FROM TipoProducto t WHERE t.nombre_tipo= :nombreTipo")
    TipoProducto findListaTypesProduct(
            @Param("nombreTipo") String nombreTipo);
}