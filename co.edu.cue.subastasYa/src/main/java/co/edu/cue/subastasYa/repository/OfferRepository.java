package co.edu.cue.subastasYa.repository;

import co.edu.cue.subastasYa.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

}
