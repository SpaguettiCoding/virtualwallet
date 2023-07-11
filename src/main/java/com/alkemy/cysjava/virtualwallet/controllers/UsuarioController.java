package com.alkemy.cysjava.virtualwallet.controllers;

import com.alkemy.cysjava.virtualwallet.model.Usuario;
import com.alkemy.cysjava.virtualwallet.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAllUsuario () {
        List<Usuario> usuario = usuarioService.findAllUsuario();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Usuario> getUsuarioById (@PathVariable("id") Long id)  {
        Usuario usuario = usuarioService.findUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.addUsuario(usuario);
        return new ResponseEntity<>(newUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        Usuario updateUsuario = usuarioService.updateUsuario(usuario);
        return new ResponseEntity<>(updateUsuario, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
