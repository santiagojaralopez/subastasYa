package co.edu.cue.subastasYa.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductoDto {

    @NotBlank
    private String nombre;

    public ProductoDto() {
    }

    public ProductoDto(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
       return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
