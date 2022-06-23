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


    /**
     * Descripcion: Aqui se obtiene la lista general de los usuarios, esto sin importar el estado en el que se encuentren.
     * @return una lista, este caso la lista de usuarios.
     */
    @GetMapping("/get-users")
    public  List<Usuario> list(){
        List<Usuario> list = usuarioService.list();
        return list;
    }


    /**
     * Descripción: Aquí obtenemos la lista de usuarios bloqueados, esto con un metodo de listar por estados. En este caso es el estado bloaqueado
     * @return
     */
    @GetMapping("/listOfBlockedUsers")
    public List<Usuario> listBlockedUsers(){
        List<Usuario> list = usuarioService.listByEstados(EstadoUsuario.BLOQUEADO);
        return list;
    }

    /**
     * Descripción: Aquí obtenemos la lista de usuarios habilitados, esto con un metodo de listar por estados. En este caso es el estado habilitado
     * @return
     */
    @GetMapping("/listOfHUsers")
    public List<Usuario> listHabilitado(){
        List<Usuario> list = usuarioService.listByEstados(EstadoUsuario.HABILITADO);
        return list;
    }

    /**
     * Descripción: Aquí obtenemos la lista de usuarios inhabilitados, esto con un metodo de listar por estados. En este caso es el estado deshabilitados
     * @return
     */
    @GetMapping("/listOfInHUsers")
    public List<Usuario> listDeshabilitado(){
        List<Usuario> list = usuarioService.listByEstados(EstadoUsuario.DESHABILITADO);
        return list;
    }

    /**
     * Descripción: En este metodo lo que se busca es obtener el usuario por su nombre de usuario y que de esta manera muestre todo lo relacionado a dicho usuario
     * Por ejemplo toda la información de registro
     * @return
     */

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

    /**
     * Descripcion: El administrador debe poder crear nuevos administradores, recordemos que el administrador también es un usuario
     * @param nuevoUsuario, el usurio de registro
     * @param bindingResult, verifica que toda la informacion que se envia al front sea adecuada, almacena errores
     * @return
     */
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

    /**
     * Descripcion: el administrador y el usuario pueden actualizar su información. Seteamos la informacion del usuario que conseguimos por medio del DTO
     * @param updateUsuarioDTO, Almacenamos aqui por asi decirlo todas las modificaciones nuevas que se le hagan al usuario
     * @param userName, Buscamos al usuario por UserName
     * @return
     */
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

    /**
     * Descripcion: para el bloqueo de usuarios lo que se realizo fue un update en los estados de la tabla estados de usuario.
     * @param userName, buscamos al usuario por su nombre de usuario.
     * @return
     */
    @PutMapping("/blockedUser/{userName}")
    public ResponseEntity<?> updateBloqueoAdmin(@PathVariable("userName")String userName){
        if(!usuarioService.existsByNombreUsuario(userName))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        Usuario usuario1= usuarioService.getByNombreUsuario(userName).get();
        usuario1.setEstadoUsuario(EstadoUsuario.BLOQUEADO);
        usuarioService.save(usuario1);
        return new ResponseEntity(new Mensaje("Usuario bloqueado exitosamente"), HttpStatus.OK);
    }



    /**
     * Descripcion: para habilitar de nuevo usuarios desde la perspectiva del administrador lo que se realizo fue un update en los estados de la tabla estados de usuario.
     * @param userName
     * @return
     */

    @PutMapping("/ActiveUser/{userName}")
    public ResponseEntity<?> updateActivarAdmiUser(@PathVariable("userName") String userName) {
        if (!usuarioService.existsByNombreUsuario(userName))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Usuario usuario1 = usuarioService.getByNombreUsuario(userName).get();
        usuario1.setEstadoUsuario(EstadoUsuario.HABILITADO);
        usuarioService.save(usuario1);
        return new ResponseEntity(new Mensaje("Habilitado de nuevo"), HttpStatus.OK);
    }

    /**
     * Descripcion: para desahbilitar usuarios desde la perspectiva del usuario lo que se realizo fue un update en los estados de la tabla estados de usuario.
     * @param userName
     * @return
     */
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
