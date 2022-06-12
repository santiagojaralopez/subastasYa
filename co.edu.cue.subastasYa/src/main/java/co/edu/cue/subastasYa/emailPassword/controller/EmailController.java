package co.edu.cue.subastasYa.emailPassword.controller;

import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.emailPassword.dto.ChangePasswordDTO;
import co.edu.cue.subastasYa.emailPassword.dto.EmailValuesDTO;
import co.edu.cue.subastasYa.emailPassword.service.EmailService;
import co.edu.cue.subastasYa.security.entity.Usuario;
import co.edu.cue.subastasYa.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/email-password")
@CrossOrigin
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    private static final String subject = "Restablecimiento de contraseña";

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailValuesDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioService.getByNombreUsuarioOrEmail(dto.getMailTo());

        if (!usuarioOpt.isPresent())
            return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"), HttpStatus.NOT_FOUND);

        Usuario usuario = usuarioOpt.get();

        dto.setMailFrom(mailFrom);
        dto.setMailTo(usuario.getEmail());
        dto.setSubject(subject);
        dto.setUserName(usuario.getNombre());

        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();

        dto.setTokenPassword(tokenPassword);

        usuario.setTokenPassword(tokenPassword);
        usuarioService.save(usuario);

        emailService.sendEmail(dto);
        return new ResponseEntity(new Mensaje("Hemos enviado un correo"), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal diligenciados"), HttpStatus.BAD_REQUEST);
        if (!dto.getPassword().equals(dto.getConfirmPassword()))
            return new ResponseEntity(new Mensaje("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);

        Optional<Usuario> usuarioOpt = usuarioService.getByTokenPassword(dto.getTokenPassword());

        if (!usuarioOpt.isPresent())
            return new ResponseEntity(new Mensaje("Parece que esta sesión ha expirado, intenta de nuevo"), HttpStatus.NOT_FOUND);

        Usuario usuario = usuarioOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(newPassword);
        usuario.setTokenPassword(null);

        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Contraseña actualizada"), HttpStatus.OK);
    }
}
