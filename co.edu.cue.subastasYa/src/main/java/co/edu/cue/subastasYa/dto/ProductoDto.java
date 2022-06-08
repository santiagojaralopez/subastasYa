package co.edu.cue.subastasYa.dto;

import co.edu.cue.subastasYa.entity.TipoProducto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductoDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private TipoProducto tipoProducto;

    public ProductoDto() {
    }

    public ProductoDto(@NotBlank String nombre,  @NotBlank TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.tipoProducto= tipoProducto;
    }

    public String getNombre() {
       return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}
