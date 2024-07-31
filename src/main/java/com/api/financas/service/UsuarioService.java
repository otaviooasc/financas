package com.api.financas.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.financas.domain.usuario.Usuario;
import com.api.financas.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario criaUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario listarUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario alterarUsuario(UUID id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNome(usuarioDetails.getNome());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deletarUsuario(UUID id){
        usuarioRepository.deleteById(id);
    }
}
