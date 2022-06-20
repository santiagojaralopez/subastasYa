package co.edu.cue.subastasYa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class TipoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtipo_producto;

    @NotBlank
    private String nombre_tipo;
    @NotBlank
    private String descripcion;

    public TipoProducto() {
    }

    public TipoProducto(String nombreTipo, String descripcion) {
        this.nombre_tipo = nombreTipo;
        this.descripcion = descripcion;
    }

    public int getIdtipo_producto() {
        return idtipo_producto;
    }

    public void setIdtipo_producto(int idtipo_producto) {
        this.idtipo_producto = idtipo_producto;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoProducto{" +
                "id=" + idtipo_producto+
                ", nombreTipo='" + nombre_tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
