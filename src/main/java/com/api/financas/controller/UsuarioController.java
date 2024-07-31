package com.api.financas.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.financas.domain.usuario.Usuario;
import com.api.financas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarTodosUsuarios() {
        return usuarioService.listarTodosUsuarios();
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return usuarioService.criaUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuario listarUsuarioPorId(@PathVariable UUID id){
        return usuarioService.listarUsuarioPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario alterarUsuario(@PathVariable UUID id, @RequestBody Usuario usuarioDetails){
        return usuarioService.alterarUsuario(id, usuarioDetails);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable UUID id){
        usuarioService.deletarUsuario(id);
    }

}
