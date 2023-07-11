package com.alkemy.cysjava.virtualwallet.service;

import com.alkemy.cysjava.virtualwallet.DTOs.UsuarioDTO;
import com.alkemy.cysjava.virtualwallet.exception.NotFoundException;
import com.alkemy.cysjava.virtualwallet.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioDTO usuarioDTO;

    @Autowired
    public UsuarioService(UsuarioDTO usuarioDTO) {
            this.usuarioDTO = usuarioDTO;
        }
    public Usuario addUsuario(Usuario usuario) {
            return usuarioDTO.save(usuario);
        }

    public List<Usuario> findAllUsuario(){
            return usuarioDTO.findAll();
        }
        
    public Usuario updateUsuario(Usuario usuario) {
            return usuarioDTO.save(usuario);
        }

    public Usuario findUsuarioById(Long id){
            return usuarioDTO.findUsuarioById(id).orElseThrow(() -> new NotFoundException("Resource by id " + id + " was not found"));
    }

    public void deleteUsuario(Long id){
            usuarioDTO.deleteUsuarioById(id);
        }
}
