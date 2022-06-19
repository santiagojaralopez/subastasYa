package co.edu.cue.subastasYa.dto;

import co.edu.cue.subastasYa.entity.TipoProducto;
import co.edu.cue.subastasYa.security.entity.Usuario;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductoDto {


    @NotNull
    private String nombre;

    @OneToOne
    @JoinColumn(name = "tipoproducto")
    private TipoProducto tipoproducto;


    @NotBlank
    private String fotoproducto;


    public ProductoDto() {
    }

    public ProductoDto(@NotNull String nombre, @NotNull String fotoProducto, TipoProducto tipoProducto) {

        this.nombre = nombre;
        this.fotoproducto = fotoProducto;
        this.tipoproducto = tipoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProducto getTipoproducto() {
        return tipoproducto;
    }

    public void setTipoproducto(TipoProducto tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    public String getFotoproducto() {
        return fotoproducto;
    }

    public void setFotoproducto(String fotoproducto) {
        this.fotoproducto = fotoproducto;
    }
}




