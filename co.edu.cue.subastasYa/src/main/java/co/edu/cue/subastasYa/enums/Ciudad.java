package co.edu.cue.subastasYa.enums;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ciudad;

    @NotNull
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "departamentoid")
    private Departamento departamento;

    public Ciudad() {
    }

    public Ciudad(int id, String nombre, Departamento departamento) {
        this.id_ciudad = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public int getId() {
        return id_ciudad;
    }

    public void setId(int id) {
        this.id_ciudad = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /*
    BOGOTA, MEDELLIN, CALI, BARRANQUILLA, CARTAGENA, SOLEDAD, CUCUTA, IBAGUE, SOACHA, VILLAVICENCIO,
    BUCARAMANGA, SANTA_MARTA, VALLEDUPAR, BELLO, PEREIRA, MONTERIA, PASTO, BUENAVENTURA, MANIZALES, NEIVA,
    PALMIRA, RIOHACHA, SINCELEJO, POPAYAN, ITAGÜI, FLORIDABLANCA, ENVIGADO, TULUA, SAN_ANDRES_DE_TUMACO, DOSQUEBRADAS,
    APARTADO, TUNJA, GIRON, URIBIA, BARRANCABERMEJA, FLORENCIA, TURBO, MAICAO, PIEDECUESTA, YOPAL

     */
}

