package co.edu.cue.subastasYa.security.service;

import co.edu.cue.subastasYa.entity.Anuncio;
import co.edu.cue.subastasYa.entity.Estado;
import co.edu.cue.subastasYa.security.entity.Usuario;
import co.edu.cue.subastasYa.security.enums.EstadoUsuario;
import co.edu.cue.subastasYa.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail) {
        return usuarioRepository.findByNombreUsuarioOrEmail(nombreOrEmail, nombreOrEmail);
    }

    public Optional<Usuario> getByTokenPassword(String tokenPassword) {
        return usuarioRepository.findByTokenPassword(tokenPassword);
    }

    public boolean existsByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }
    public boolean existsById(int id){
        return usuarioRepository.existsById(id);
    }

    public boolean existsByIdUser(int id){
        return usuarioRepository.existsById(id);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> getOne(int id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> getByNombre(String nombre){
        return usuarioRepository.findByNombreUsuario(nombre);
    }

    public List<Usuario> listByEstados(EstadoUsuario estadoUsuario) {
        if (estadoUsuario == EstadoUsuario.BLOQUEADO)
            return usuarioRepository.findUsuarioByEstado(EstadoUsuario.BLOQUEADO);
        if (estadoUsuario == EstadoUsuario.HABILITADO)
            return usuarioRepository.findUsuarioByEstado(EstadoUsuario.HABILITADO);
        return usuarioRepository.findUsuarioByEstado(EstadoUsuario.DESHABILITADO);
    }


}
