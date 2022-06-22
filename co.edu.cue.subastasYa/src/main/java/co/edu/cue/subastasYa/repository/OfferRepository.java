package co.edu.cue.subastasYa.repository;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Offer;
import co.edu.cue.subastasYa.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    @Query("SELECT o FROM Offer o WHERE o.anuncio= :anuncio")
    List<Offer> getOffersByAnnouncement(
            @Param("anuncio") Anuncio anuncio
    );

    @Query("SELECT o FROM Offer o WHERE o.anuncio= :anuncio AND o.bidderUser= :bidderUser")
    List<Offer> getOffersByAnnouncementAndBidderUser(
            @Param("anuncio") Anuncio anuncio, @Param("bidderUser") Usuario BidderUser
    );
}
