package co.edu.cue.subastasYa.dto;

import co.edu.cue.subastasYa.security.enums.EstadoUsuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UsuariosDto {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    private String numeroDoc;

    @NotBlank
    private Date fechanacto;

    @NotBlank
    private String direccion;

    @NotNull
    private String email;

    @NotBlank
    private String nombreUsuario;

    @NotBlank
    private String password;

    @NotBlank
    private EstadoUsuario estado;




    public UsuariosDto(@NotBlank String nombre, @NotBlank String apellido, @NotBlank String numeroDoc, @NotBlank Date fechanacto, @NotBlank String direccion, @NotBlank String email ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDoc = numeroDoc;
        this.fechanacto = fechanacto;
        this.direccion = direccion;
        this.email = email;
    }

    public UsuariosDto( @NotBlank String nombre, @NotBlank String apellido, @NotBlank String numeroDoc,@NotBlank Date fechanacto, @NotBlank String direccion, @NotBlank String email, @NotBlank String nombreUsuario, @NotBlank String password, @NotBlank EstadoUsuario estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDoc = numeroDoc;
        this.fechanacto = fechanacto;
        this.direccion = direccion;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.estado = estado;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public Date getFechanacto() {
        return fechanacto;
    }

    public void setFechanacto(Date fechanacto) {
        this.fechanacto = fechanacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }
}
