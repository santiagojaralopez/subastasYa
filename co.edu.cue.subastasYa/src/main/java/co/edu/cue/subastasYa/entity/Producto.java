package co.edu.cue.subastasYa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nombre;

    @NotNull
    private String foto_producto;

    @OneToOne
    @JoinColumn(name = "tipoproducto")
    private TipoProducto tipoproducto;

    public Producto() {
    }

    public Producto(String nombre, String fotoProducto, TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.foto_producto=fotoProducto;
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

    public String getFoto_producto() {
        return foto_producto;
    }

    public void setFoto_producto(String foto_producto) {
        this.foto_producto = foto_producto;
    }

    public TipoProducto getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(TipoProducto tipoproducto) {
        this.tipoproducto = tipoproducto;
    }
}
