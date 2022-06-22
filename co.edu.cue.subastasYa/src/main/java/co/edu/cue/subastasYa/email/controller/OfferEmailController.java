package co.edu.cue.subastasYa.email.controller;

import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.email.dto.OfferEmailValueDTO;
import co.edu.cue.subastasYa.email.service.EmailService;
import co.edu.cue.subastasYa.security.service.UsuarioService;
import co.edu.cue.subastasYa.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class OfferEmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AnuncioService anuncioService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private static final String subject = "¡Han ofertado por tu anuncio!";

    public ResponseEntity<?> sendEmail(@RequestBody OfferEmailValueDTO dto) {
        /**Optional<Usuario> usuarioAnuncianteOpt = usuarioService.getByNombreUsuarioOrEmail(dto.getMailTo());
        Optional<Usuario> usuarioOfertanteOpt = usuarioService.getByNombreUsuarioOrEmail(dto.getBidderUserName());

        Usuario usuarioAnunciante = usuarioAnuncianteOpt.get();
        Usuario usuarioOfertante = usuarioOfertanteOpt.get();

        Optional<Anuncio> anuncioOpt = anuncioService.findAnuncioByUsuario(usuarioAnunciante);
        Anuncio anuncio = anuncioOpt.get();

        dto.setMailFrom(mailFrom);
        dto.setMailTo(usuarioAnunciante.getEmail());
        dto.setSubject(subject);
        dto.setUserName(usuarioAnunciante.getNombre());
        dto.setPublicationName(anuncio.getProducto().getNombre());
        dto.setBidderUserName(usuarioOfertante.getNombreUsuario()); **/

        emailService.sendOfferEmail(dto);
        return new ResponseEntity(new Mensaje("Hemos enviado un correo"), HttpStatus.OK);
    }
}
