package co.edu.cue.subastasYa.security.controller;

import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.security.dto.JwtDto;
import co.edu.cue.subastasYa.security.dto.LoginUsuario;
import co.edu.cue.subastasYa.security.dto.NuevoUsuario;
import co.edu.cue.subastasYa.security.entity.Rol;
import co.edu.cue.subastasYa.security.entity.Usuario;
import co.edu.cue.subastasYa.security.enums.EstadoUsuario;
import co.edu.cue.subastasYa.security.enums.RolNombre;
import co.edu.cue.subastasYa.security.service.RolService;
import co.edu.cue.subastasYa.security.service.UsuarioService;
import co.edu.cue.subastasYa.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        Date input = nuevoUsuario.getFechaNacimiento();
        LocalDate fechaNacimiento = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal diligenciados o email inválido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Ese Email ya está registrado"), HttpStatus.BAD_REQUEST);
        if(validarFecha(fechaNacimiento)){
            return new ResponseEntity(new Mensaje("El usuario es menor de edad"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nuevoUsuario.getNombres());
        usuario.setApellido(nuevoUsuario.getApellidos());
        usuario.setNombreUsuario(nuevoUsuario.getNombreUsuario());
        usuario.setEmail(nuevoUsuario.getEmail());
        usuario.setTipoDocumento(nuevoUsuario.getTipoDocumento());
        usuario.setNumerodoc(nuevoUsuario.getNumeroDocumento());
        usuario.setFechanacto(nuevoUsuario.getFechaNacimiento());
        usuario.setDepartamento(nuevoUsuario.getDepartamento());
        usuario.setDireccion(nuevoUsuario.getDireccion());
        usuario.setEstadoUsuario(EstadoUsuario.HABILITADO);
        usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        if (nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());

        usuario.setRoles(roles);
        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal diligenciados"), HttpStatus.BAD_REQUEST);

        Optional<Usuario> usuario = usuarioService.getByNombreUsuario(loginUsuario.getNombreUsuario());

        if (usuario.get().getEstadoUsuario() == EstadoUsuario.BLOQUEADO)
            return new ResponseEntity(new Mensaje("No puedes iniciar sesión, tu cuenta se encuentra bloqueada. Por favor comunícate a subastasya@gmail.com para solucionar este inconveniente."), HttpStatus.BAD_REQUEST);

        if (usuario.get().getEstadoUsuario() == EstadoUsuario.DESHABILITADO)
            return new ResponseEntity(new Mensaje("Hemos notado que te diste de baja. Por favor comunícate a subastasya@gmail.com para recuperar tu usuario."), HttpStatus.BAD_REQUEST);

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginUsuario.getNombreUsuario(),
                        loginUsuario.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    public Boolean validarFecha(LocalDate fechanacto ) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ahora = LocalDate.now();

        long periodo2 = ChronoUnit.DAYS.between(fechanacto, ahora);

        if (periodo2 >= 18*365) {
            return false;
        }else{
            return true;
        }
    }

}
