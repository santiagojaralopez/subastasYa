package co.edu.cue.subastasYa.controller;

import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.dto.OfferDTO;
import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Offer;
import co.edu.cue.subastasYa.security.entity.Usuario;
import co.edu.cue.subastasYa.security.service.UsuarioService;
import co.edu.cue.subastasYa.service.AnuncioService;
import co.edu.cue.subastasYa.service.ConfiguracionService;
import co.edu.cue.subastasYa.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    OfferService offerService;

    @Autowired
    AnuncioService anuncioService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ConfiguracionService configService;

    /**
     * Creates an offer related to a user and an announcement
     * @param offerDto: OfferDTO
     * @param bindingResult: BindingResult
     * @return ResponseEntity with a message and Http Status
     */
    @PostMapping("/new")
    public ResponseEntity<?> newOffer(@Valid @RequestBody OfferDTO offerDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Error?"), HttpStatus.BAD_REQUEST);

        Optional<Usuario> usuario = usuarioService.getByNombreUsuario(offerDto.getBidderUserName());
        Optional<Anuncio> anuncio = anuncioService.getOne(offerDto.getAnnouncementId());
        List<Offer> offers = offerService.getOffersByAnnouncementAndBidderUser(anuncio.get(), usuario.get());

        if (offers.size() < configService.cantidadMaximaOfertas()) {
            Offer offer = new Offer();
            offer.setOfferValue(offerDto.getOfferValue());
            offer.setBidderUser(usuario.get());
            offer.setAnuncio(anuncio.get());

            offerService.save(offer);

            return new ResponseEntity(new Mensaje("Puja realizada"), HttpStatus.CREATED);
        }
        return new ResponseEntity(new Mensaje("No puedes realizar más pujas por este anuncio, has alcanzado el máximo"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Gets a list of offers related to an announcement
     * @param announcementId: int
     * @return List of offers
     */
    @GetMapping("/announcement-offers/{announcementId}")
    public List<Offer> getOffersByAnnouncement(@PathVariable("announcementId") int announcementId) {
        Optional<Anuncio> anuncio = anuncioService.getOne(announcementId);
        return offerService.getOffersByAnnouncementId(anuncio.get());
    }

    /**
     * Deletes all the other offers that are not the winner one
     * @param offerId: int
     */
    @DeleteMapping("/delete-offers/{offerId}")
    public void deleteOffers(@PathVariable("offerId") int offerId) {
        offerService.deleteOffers(offerId);
    }
}
