package co.edu.cue.subastasYa.security.entity;

import co.edu.cue.subastasYa.security.enums.EstadoUsuario;
import co.edu.cue.subastasYa.security.enums.TipoDocumento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    @Column(unique=true)
    private String nombreUsuario;

    @NotNull
    private String email;

    @NotNull
    private String tipoDocumento;

    @NotNull
    private String numerodoc;

    @NotNull
    private Date fechanacto;

    @NotNull
    private String departamento;

    @NotNull
    private String direccion;

    @NotNull
    private EstadoUsuario estadoUsuario;

    @NotNull
    private String password;

    private String tokenPassword;

    @NotNull
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="usuario_rol",
            joinColumns=@JoinColumn(name="usuario_id"),
            inverseJoinColumns=@JoinColumn(name="rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String nombreUsuario, String email, String tipoDocumento, String numerodoc, Date fechanacto, String departamento, String direccion, EstadoUsuario estadoUsuario, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.tipoDocumento = tipoDocumento;
        this.numerodoc = numerodoc;
        this.fechanacto = fechanacto;
        this.departamento = departamento;
        this.direccion = direccion;
        this.estadoUsuario = estadoUsuario;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumerodoc() {
        return numerodoc;
    }

    public void setNumerodoc(String numerodoc) {
        this.numerodoc = numerodoc;
    }

    public Date getFechanacto() {
        return fechanacto;
    }

    public void setFechanacto(Date fechanacto) {
        this.fechanacto = fechanacto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenPassword() {
        return tokenPassword;
    }

    public void setTokenPassword(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
