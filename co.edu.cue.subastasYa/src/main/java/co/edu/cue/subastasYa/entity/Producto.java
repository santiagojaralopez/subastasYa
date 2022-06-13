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


    @NotBlank
    private String fotoProducto;


    public Producto() {
    }

    public Producto(String nombre, String fotoProducto) {
        this.nombre = nombre;
        this.fotoProducto=fotoProducto;
        //this.tipoProducto= tipoProducto;
    }

    //public Producto(String nombre, TipoProducto tipoProducto) {
      //  this.nombre = nombre;
        // this.tipoProducto= tipoProducto;
    //}

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

    public String getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }
}
