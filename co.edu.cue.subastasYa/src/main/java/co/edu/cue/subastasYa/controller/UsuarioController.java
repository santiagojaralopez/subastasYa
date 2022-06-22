package co.edu.cue.subastasYa.controller;

import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.dto.UpdateUsuarioDTO;
import co.edu.cue.subastasYa.security.dto.NuevoUsuario;
import co.edu.cue.subastasYa.security.entity.Rol;
import co.edu.cue.subastasYa.security.entity.Usuario;
import co.edu.cue.subastasYa.security.enums.EstadoUsuario;
import co.edu.cue.subastasYa.security.enums.RolNombre;
import co.edu.cue.subastasYa.security.service.RolService;
import co.edu.cue.subastasYa.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/get-users")
    public  List<Usuario> list(){
        List<Usuario> list = usuarioService.list();
        return list;
    }

    @GetMapping("/listOfBlockedUsers")
    public List<Usuario> listBlockedUsers(){
        List<Usuario> list = usuarioService.listByEstados(EstadoUsuario.BLOQUEADO);
        return list;
    }

    @GetMapping("/listOfHUsers")
    public List<Usuario> listHabilitado(){
        List<Usuario> list = usuarioService.listByEstados(EstadoUsuario.HABILITADO);
        return list;
    }

    @GetMapping("/listOfInHUsers")
    public List<Usuario> listDeshabilitado(){
        List<Usuario> list = usuarioService.listByEstados(EstadoUsuario.DESHABILITADO);
        return list;
    }

    @GetMapping("/detail-user/{nombreUsuario}")
    public ResponseEntity getById(@PathVariable("nombreUsuario") String nombreUsuario) {
        Optional<Usuario> usuario = usuarioService.getByNombreUsuario(nombreUsuario);

        if (!usuario.isPresent())
            return new ResponseEntity(new Mensaje("Usuario no encontrado"), HttpStatus.NOT_FOUND);

        UpdateUsuarioDTO updateUsuarioDTO = new UpdateUsuarioDTO();
        updateUsuarioDTO.setNombre(usuario.get().getNombre());
        updateUsuarioDTO.setApellido(usuario.get().getApellido());
        updateUsuarioDTO.setNombreUsuario(usuario.get().getNombreUsuario());
        updateUsuarioDTO.setEmail(usuario.get().getEmail());
        updateUsuarioDTO.setTipoDocumento(usuario.get().getTipoDocumento());
        updateUsuarioDTO.setNumerodoc(usuario.get().getNumerodoc());
        updateUsuarioDTO.setDireccion(usuario.get().getDireccion());

        return new ResponseEntity(updateUsuarioDTO, HttpStatus.OK);
    }

    @PostMapping("/newUser")
    public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Ese Email ya está registrado"), HttpStatus.BAD_REQUEST);

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
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());

        usuario.setRoles(roles);
        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/updateUser/{userName}")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateUsuarioDTO updateUsuarioDTO, @PathVariable String userName) {
        if (!userName.equals(updateUsuarioDTO.getNombreUsuario())) {
            if(usuarioService.existsByNombreUsuario(updateUsuarioDTO.getNombreUsuario())) {
                return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
            }
        }

        Usuario usuario = usuarioService.getByNombreUsuarioOrEmail(userName).get();
        usuario.setNombre(updateUsuarioDTO.getNombre());
        usuario.setApellido(updateUsuarioDTO.getApellido());
        usuario.setNombreUsuario(updateUsuarioDTO.getNombreUsuario());
        usuario.setEmail(updateUsuarioDTO.getEmail());
        usuario.setTipoDocumento(updateUsuarioDTO.getTipoDocumento());
        usuario.setNumerodoc(updateUsuarioDTO.getNumerodoc());
        usuario.setDepartamento(updateUsuarioDTO.getDepartamento());
        usuario.setDireccion(updateUsuarioDTO.getDireccion());

        try {
            usuarioService.save(usuario);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Por favor no deje campos vacíos"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new Mensaje("usuario actualizado"), HttpStatus.OK);
    }

    @PutMapping("/blockedUser/{userName}")
    public ResponseEntity<?> updateBloqueoAdmin(@PathVariable("userName")String userName){
        if(!usuarioService.existsByNombreUsuario(userName))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        Usuario usuario1= usuarioService.getByNombreUsuario(userName).get();
        usuario1.setEstadoUsuario(EstadoUsuario.BLOQUEADO);
        usuarioService.save(usuario1);
        return new ResponseEntity(new Mensaje("Usuario bloqueado exitosamente"), HttpStatus.OK);
    }

    @PutMapping("/ActiveUser/{userName}")
    public ResponseEntity<?> updateActivarAdmiUser(@PathVariable("userName") String userName) {
        if (!usuarioService.existsByNombreUsuario(userName))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario1 = usuarioService.getByNombreUsuario(userName).get();
        usuario1.setEstadoUsuario(EstadoUsuario.HABILITADO);
        usuarioService.save(usuario1);
        return new ResponseEntity(new Mensaje("Habilitado de nuevo"), HttpStatus.OK);
    }

    @PutMapping("/DeshabilitarUser/{userName}")
    public ResponseEntity<?> updateDeshabilitarUserFromUser(@PathVariable("userName")String userName) {
        if (!usuarioService.existsByNombreUsuario(userName))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario1 = usuarioService.getByNombreUsuario(userName).get();
        usuario1.setEstadoUsuario(EstadoUsuario.DESHABILITADO);
        usuarioService.save(usuario1);
        return new ResponseEntity(new Mensaje("El usuario se ha dado de baja"), HttpStatus.OK);
    }


}
