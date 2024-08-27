package com.api.financas.controller;

import com.api.financas.domain.usuario.Usuario;
import com.api.financas.exceptions.GenericaException;
import com.api.financas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<Object> listarTodosUsuarios() {
        return ResponseEntity.ok().body(usuarioService.listarTodos());
    }

    @PostMapping("/criar")
    public ResponseEntity<Object> criarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.criar(usuario));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Object> listarUsuarioPorId(@PathVariable UUID id) throws GenericaException {
        return ResponseEntity.ok().body(usuarioService.listarPorId(id));
    }

    @GetMapping("/listar/email/{email}")
    public ResponseEntity<Object> listarUsuarioPorEmail(@PathVariable String email) throws GenericaException {
        return ResponseEntity.ok().body(usuarioService.listarPorEmail(email));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Object> alterarUsuario(@PathVariable UUID id, @RequestBody Usuario usuarioDetails) throws GenericaException {
        return ResponseEntity.ok().body(usuarioService.alterar(id, usuarioDetails));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable UUID id) throws GenericaException {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
