package co.edu.cue.subastasYa.controller;

import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.dto.OfferDTO;
import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Offer;
import co.edu.cue.subastasYa.security.entity.Usuario;
import co.edu.cue.subastasYa.security.service.UsuarioService;
import co.edu.cue.subastasYa.service.AnuncioService;
import co.edu.cue.subastasYa.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     * Description: This method creates an offer related to a user and an announcement
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

        Offer offer = new Offer();
        offer.setOfferValue(offerDto.getOfferValue());
        offer.setBidderUser(usuario.get());
        offer.setAnuncio(anuncio.get());

        offerService.save(offer);

        return new ResponseEntity(new Mensaje("Puja realizada"), HttpStatus.CREATED);
    }
}
