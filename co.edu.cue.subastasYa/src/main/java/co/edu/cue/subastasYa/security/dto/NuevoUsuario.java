package co.edu.cue.subastasYa.security.dto;

import co.edu.cue.subastasYa.security.enums.EstadoUsuario;
import co.edu.cue.subastasYa.security.enums.TipoDocumento;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NuevoUsuario {
    @NotBlank
    private String nombre;
    @NotBlank
    private String nombre_Usuario;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String apellido;
    @NotBlank
    private String numero_doc;
    @NotBlank
    private Date fecha_nacto;
    @NotBlank
    private String direccion;
    @NotBlank
    private EstadoUsuario estadoUsuario;
    @NotBlank
    private TipoDocumento tipoDocumento;

    private Set<String> roles = new HashSet<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombre_Usuario;
    }

    public void setNombre_Usuario(String nombreUsuario) {
        this.nombre_Usuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getNombre_Usuario() {
        return nombre_Usuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero_doc() {
        return numero_doc;
    }

    public void setNumero_doc(String numero_doc) {
        this.numero_doc = numero_doc;
    }

    public Date getFecha_nacto() {
        return fecha_nacto;
    }

    public void setFecha_nacto(Date fecha_nacto) {
        this.fecha_nacto = fecha_nacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
