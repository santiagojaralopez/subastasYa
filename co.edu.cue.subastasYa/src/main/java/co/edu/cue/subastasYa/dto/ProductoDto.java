package co.edu.cue.subastasYa.dto;

import co.edu.cue.subastasYa.entity.TipoProducto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductoDto {


    @NotNull
    private String nombre;

    //private TipoProducto tipoProducto;

    @NotBlank
    private String fotoProducto;


    public ProductoDto() {
    }


    public ProductoDto(@NotNull String nombre, @NotNull String fotoProducto) {
        this.nombre = nombre;
        this.fotoProducto = fotoProducto;
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
}
