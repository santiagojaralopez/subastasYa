package co.edu.cue.subastasYa.entity;

import co.edu.cue.subastasYa.security.entity.Usuario;

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

<<<<<<< HEAD
=======
    //@NotNull
    //private TipoProducto tipoProducto;


>>>>>>> santiago-gallego
    @NotBlank
    private String foto_Producto;


    @OneToOne
    @JoinColumn(name = "tipoproducto")
    private TipoProducto tipoProducto;





    public Producto() {
    }

    public Producto(String nombre, String fotoProducto, TipoProducto tipoProducto) {
        this.nombre = nombre;
<<<<<<< HEAD
        this.fotoProducto=fotoProducto;
        this.tipoProducto= tipoProducto;
=======
        this.foto_Producto=fotoProducto;
        //this.tipoProducto= tipoProducto;
>>>>>>> santiago-gallego
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

<<<<<<< HEAD
    public String getFotoProducto() {
        return fotoProducto;
    }
=======
    //public TipoProducto getTipoProducto() {
    //   return tipoProducto;
    //}

    // public void setTipoProducto(TipoProducto tipoProducto) {
    //    this.tipoProducto = tipoProducto;
    // }

    //public String getFotoProducto() {
       // return fotoProducto;
    //}
>>>>>>> santiago-gallego

    public void setFotoProducto(String fotoProducto) {
      this.foto_Producto = fotoProducto;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}
