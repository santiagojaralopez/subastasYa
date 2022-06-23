package co.edu.cue.subastasYa.service;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Offer;
import co.edu.cue.subastasYa.repository.OfferRepository;
import co.edu.cue.subastasYa.security.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    public List<Offer> list(){
        return offerRepository.findAll();
    }

    public Optional<Offer> getOfferById(int id) {
        return offerRepository.findById(id);
    }

    public void save(Offer offer) {
        offerRepository.save(offer);
    }

    public List<Offer> getOffersByAnnouncementId(Anuncio anuncio) {
        return offerRepository.getOffersByAnnouncement(anuncio);
    }

    public List<Offer> getOffersByAnnouncementAndBidderUser(Anuncio anuncio, Usuario bidderUser) {
        return offerRepository.getOffersByAnnouncementAndBidderUser(anuncio, bidderUser);
    }

    public void deleteOffers(int offerId) {
        offerRepository.deleteOffers(offerId);
    }
}
