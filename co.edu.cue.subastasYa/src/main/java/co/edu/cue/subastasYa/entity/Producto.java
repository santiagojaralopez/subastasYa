package co.edu.cue.subastasYa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nombre;

    //@NotNull
    //private TipoProducto tipoProducto;


    @NotBlank
    private String foto_Producto;


    public Producto() {
    }

    public Producto(String nombre, String fotoProducto) {
        this.nombre = nombre;
        this.foto_Producto=fotoProducto;
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

    //public String getFotoProducto() {
       // return fotoProducto;
    //}

    public void setFotoProducto(String fotoProducto) {
      this.foto_Producto = fotoProducto;
    }
}
