package co.edu.cue.subastasYa.service;

import co.edu.cue.subastasYa.entity.Offer;
import co.edu.cue.subastasYa.repository.OfferRepository;
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
}
