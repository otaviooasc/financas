package com.api.financas.service;

import com.api.financas.domain.usuario.Usuario;
import com.api.financas.dto.user.UsuarioLoginDTO;
import com.api.financas.exceptions.GenericaException;
import com.api.financas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario criar(Usuario usuario) {
        usuario.setPrimeiroAcesso(LocalDate.now());
        return usuarioRepository.save(usuario);
    }

    public Usuario listarPorId(UUID id) throws GenericaException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new GenericaException("Nenhum usuario encontrado com esse id: " + id));
    }

    public Usuario alterar(UUID id, Usuario usuarioDetails) throws GenericaException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new GenericaException("Nenhum usuario encontrado com esse id: " + id));

        usuario.setNome(usuarioDetails.getNome());
        usuario.setPassword(usuarioDetails.getPassword());
        usuario.setEmail(usuarioDetails.getEmail());
        return usuarioRepository.save(usuario);
    }

    public void deletar(UUID id) throws GenericaException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new GenericaException("Nenhum usuario encontrado com esse id: " + id));
        usuarioRepository.deleteById(id);
    }

    public Object listarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return new UsuarioLoginDTO(usuario.getId() + "", usuario.getNome(), usuario.getEmail());
    }
}
