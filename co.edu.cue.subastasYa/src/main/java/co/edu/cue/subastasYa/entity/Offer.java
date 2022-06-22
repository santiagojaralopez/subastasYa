package co.edu.cue.subastasYa.entity;

import co.edu.cue.subastasYa.security.entity.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_oferta;

    @NotNull
    private double offerValue;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull
    private Usuario bidderUser;

    @ManyToOne
    @JoinColumn(name = "id_anuncio")
    @NotNull
    private Anuncio anuncio;

    public Offer() {
    }

    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public double getOfferValue() {
        return offerValue;
    }

    public void setOfferValue(double offerValue) {
        this.offerValue = offerValue;
    }

    public Usuario getBidderUser() {
        return bidderUser;
    }

    public void setBidderUser(Usuario bidderUser) {
        this.bidderUser = bidderUser;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}
