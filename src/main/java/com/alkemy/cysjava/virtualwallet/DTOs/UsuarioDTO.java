package com.alkemy.cysjava.virtualwallet.DTOs;

import com.alkemy.cysjava.virtualwallet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDTO extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioById(Long id);

    void deleteUsuarioById(Long id);
}
