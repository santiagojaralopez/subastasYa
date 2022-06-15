package co.edu.cue.subastasYa.dto;

import co.edu.cue.subastasYa.entity.TipoProducto;
import co.edu.cue.subastasYa.security.entity.Usuario;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductoDto {


    @NotBlank
    private String nombre;

    @OneToOne
    @JoinColumn(name = "tipoProducto_id")
    private TipoProducto tipoProducto;


    @NotBlank
    private String fotoProducto;




    public ProductoDto() {
    }


    public ProductoDto(@NotBlank String nombre, @NotBlank String fotoProducto, @NotBlank TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.fotoProducto = fotoProducto;
        this.tipoProducto= tipoProducto;
    }

    public String getNombre() {
       return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
       this.tipoProducto = tipoProducto;
    }
}
