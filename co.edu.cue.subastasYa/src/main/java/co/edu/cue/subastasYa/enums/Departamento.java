package co.edu.cue.subastasYa.enums;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_departamento;

    @NotNull
    private String nombre;

    public Departamento() {
    }

    public Departamento(int id, String nombre) {
        this.id_departamento = id;
        this.nombre = nombre;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
    AMAZONAS, ANTIOQUÍA, ARAUCA, ATLÁNTICO, BOLÍVAR, BOYACÁ, CALDAS, CAQUETÁ, CASANARE, CAUCA, CESAR, CHOCÓ,
    CÓRDOBA, CUNDINAMARCA, GUAINÍA, GUAVIARE, HUILA, LA_GUAJIRA,MAGDALENA,META,NARIÑO,NORTE_DE_SANTANDER,
    PUTUMAYO, QUINDÍO, RISARALDA, SAN_ANDRÉS_Y_PROVIDENCIA, SANTANDER, SUCRE, TOLIMA, VALLE_DEL_CAUCA,VAUPÉS,VICHADA

     */
}
