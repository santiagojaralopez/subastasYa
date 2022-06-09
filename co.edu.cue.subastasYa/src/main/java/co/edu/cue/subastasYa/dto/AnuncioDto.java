package co.edu.cue.subastasYa.dto;

import co.edu.cue.subastasYa.entity.Ciudad;
import co.edu.cue.subastasYa.entity.Departamento;
import co.edu.cue.subastasYa.entity.Estado;
import co.edu.cue.subastasYa.entity.Producto;
import co.edu.cue.subastasYa.security.entity.Usuario;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class AnuncioDto {

    @NotBlank
    private String descripcion;
    @NotBlank
    private Date fecha_inicio;
    @NotBlank
    private Date fecha_fin;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @NotBlank
    private Usuario usuario;


    @NotBlank
    private Estado estado;
    @NotBlank
    private Ciudad ciudad;
    @NotBlank
    private Departamento departamento;
    @NotBlank
    private Double valor;

    @OneToOne
    @JoinColumn(name = "producto_id")
    @NotBlank
    private Producto producto;


    public AnuncioDto() {
    }


    public AnuncioDto(@NotBlank String descripcion,@NotBlank Date fecha_inicio,@NotBlank Date fecha_fin,@NotBlank Usuario usuario,@NotBlank Estado estado,@NotBlank Ciudad ciudad,@NotBlank Departamento departamento,@NotBlank Double valor,@NotBlank Producto producto) {
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.usuario = usuario;
        this.estado = estado;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.valor = valor;
        this.producto = producto;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
