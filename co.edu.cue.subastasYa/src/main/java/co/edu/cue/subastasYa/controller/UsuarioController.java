package co.edu.cue.subastasYa.controller;

import co.edu.cue.subastasYa.dto.Mensaje;
import co.edu.cue.subastasYa.dto.UsuariosDto;
import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.security.dto.NuevoUsuario;
import co.edu.cue.subastasYa.security.entity.Rol;
import co.edu.cue.subastasYa.security.entity.Usuario;
import co.edu.cue.subastasYa.security.enums.EstadoUsuario;
import co.edu.cue.subastasYa.security.enums.RolNombre;
import co.edu.cue.subastasYa.security.service.RolService;
import co.edu.cue.subastasYa.security.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

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

    @GetMapping("/detail-user/{id}")
    public ResponseEntity getById(@PathVariable("id") int id){
        if(!usuarioService.existsByIdUser(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.getOne(id).get();
        return new ResponseEntity(usuario, HttpStatus.OK);
    }

    @PostMapping("/newUser")
    public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Ese Email ya está registrado"), HttpStatus.BAD_REQUEST);


        Usuario usuario = new Usuario(nuevoUsuario.getNombres(), nuevoUsuario.getApellidos(), nuevoUsuario.getNumeroDocumento(), nuevoUsuario.getFechaNacimiento(),nuevoUsuario.getDireccion(), EstadoUsuario.HABILITADO, nuevoUsuario.getTipoDocumento(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), nuevoUsuario.getPassword());

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());

        usuario.setRoles(roles);
        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/updateUser/{userName}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody UsuariosDto usuariodto){
        if(!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(usuarioService.existsByNombreUsuario(usuariodto.getNombre()) && usuarioService.getByNombre(usuariodto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuariodto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuariodto.getApellido()))
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuariodto.getDireccion()))
            return new ResponseEntity(new Mensaje("la direccion es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank((CharSequence) usuariodto.getFechanacto()))
            return new ResponseEntity(new Mensaje("la fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank((CharSequence) usuariodto.getEmail()))
            return new ResponseEntity(new Mensaje("el email es obligatorio"), HttpStatus.BAD_REQUEST);

        Usuario usuario = usuarioService.getOne(id).get();
        usuario.setNombre(usuariodto.getNombre());
        usuario.setApellido(usuariodto.getApellido());
        usuario.setFechanacto(usuariodto.getFechanacto());
        usuario.setDireccion(usuariodto.getDireccion());
        usuario.setNumerodoc(usuariodto.getNumeroDoc());
        usuario.setEmail(usuariodto.getEmail());

        usuarioService.save(usuario);
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
