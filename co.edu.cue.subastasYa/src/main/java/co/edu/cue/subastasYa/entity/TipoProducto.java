package co.edu.cue.subastasYa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

public class TipoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nombreTipo;
    @NotBlank
    private String descripcion;



    public TipoProducto() {
    }

    public TipoProducto(int id, String nombreTipo, String descripcion) {
        this.id = id;
        this.nombreTipo = nombreTipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
