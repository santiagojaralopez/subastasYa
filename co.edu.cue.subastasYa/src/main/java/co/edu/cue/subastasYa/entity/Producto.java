package co.edu.cue.subastasYa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nombre;

    //@NotBlank
    //private TipoProducto tipoProducto;



    public Producto() {
    }

    public Producto(String nombre, TipoProducto tipoProducto) {
        this.nombre = nombre;
        //this.tipoProducto= tipoProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public String getNombre() {
       return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //public TipoProducto getTipoProducto() {
    //   return tipoProducto;
    //}

    // public void setTipoProducto(TipoProducto tipoProducto) {
    //    this.tipoProducto = tipoProducto;
    // }
}
