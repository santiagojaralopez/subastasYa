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

    //@NotNull
    //private TipoProducto tipoProducto;

    @NotBlank
    private String foto_Producto;

    @OneToOne
    @JoinColumn(name = "tipoproducto")
    private TipoProducto tipoproducto;

    public Producto() {
    }

    public Producto(String nombre, String fotoProducto, TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.foto_Producto=fotoProducto;
        this.tipoproducto= tipoProducto;
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

    public String getFoto_Producto() {
        return foto_Producto;
    }

    public void setFoto_Producto(String foto_Producto) {
        this.foto_Producto = foto_Producto;
    }

    public TipoProducto getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(TipoProducto tipoproducto) {
        this.tipoproducto = tipoproducto;
    }
}
