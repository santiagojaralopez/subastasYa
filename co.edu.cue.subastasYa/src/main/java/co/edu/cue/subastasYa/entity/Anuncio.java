package co.edu.cue.subastasYa.entity;

import co.edu.cue.subastasYa.security.entity.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_anuncio;


    @NotNull
    private String descripcion;
    //@NotNull
    private Date fecha_inicio;
    //@NotNull
    private Date fecha_fin;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @NotNull
    private Usuario usuario;


    @NotNull
    private Estado estado;
    @NotNull
    private Ciudad ciudad;
    @NotNull
    private Departamento departamento;
    @NotNull
    private Double valor;


    @OneToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


    public Anuncio() {
    }

    public Anuncio(String descripcion, Date fecha_inicio, Date fecha_fin, Usuario usuario, Estado estado, Ciudad ciudad, Departamento departamento, Double valor, Producto producto) {
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

    public int getId_anuncio() {
        return id_anuncio;
    }

    public void setId_anuncio(int id_anuncio) {
        this.id_anuncio = id_anuncio;
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
