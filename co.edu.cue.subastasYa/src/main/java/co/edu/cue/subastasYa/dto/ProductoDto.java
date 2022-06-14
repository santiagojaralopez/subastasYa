package co.edu.cue.subastasYa.dto;

import co.edu.cue.subastasYa.entity.TipoProducto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductoDto {


    @NotBlank
    private String nombre;

    //private TipoProducto tipoProducto;

    @NotBlank
    private String fotoProducto;


    public ProductoDto() {
    }


    public ProductoDto(@NotBlank String nombre, @NotBlank String fotoProducto) {
        this.nombre = nombre;
        this.fotoProducto = fotoProducto;
        //this.tipoProducto= tipoProducto;
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

    //public TipoProducto getTipoProducto() {
    //    return tipoProducto;
    //}

    //public void setTipoProducto(TipoProducto tipoProducto) {
    //   this.tipoProducto = tipoProducto;
    //}
}
