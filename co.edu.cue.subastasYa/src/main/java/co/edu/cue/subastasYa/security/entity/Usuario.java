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
    private String numero_doc;

    @NotNull
    private Date fecha_nacto;

    @NotNull
    private String direccion;

    @NotNull
    private EstadoUsuario estadoUsuario;

    @NotNull
    private TipoDocumento tipoDocumento;


    @NotNull
    @Column(unique=true)
    private String nombreUsuario;

    @NotNull
    private String email;

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

    public Usuario( String nombre, String apellido, String numero_doc, Date fecha_nacto, String direccion, EstadoUsuario estadoUsuario, TipoDocumento tipoDocumento, String nombreUsuario, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero_doc = numero_doc;
        this.fecha_nacto = fecha_nacto;
        this.direccion = direccion;
        this.estadoUsuario = estadoUsuario;
        this.tipoDocumento = tipoDocumento;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    public Usuario( String nombre, String apellido, String numero_doc, Date fecha_nacto, String direccion, EstadoUsuario estadoUsuario, TipoDocumento tipoDocumento, String nombreUsuario, String email, String password, Set<Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero_doc = numero_doc;
        this.fecha_nacto = fecha_nacto;
        this.direccion = direccion;
        this.estadoUsuario = estadoUsuario;
        this.tipoDocumento = tipoDocumento;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTokenPassword() {
        return tokenPassword;
    }

    public void setTokenPassword(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }
}
