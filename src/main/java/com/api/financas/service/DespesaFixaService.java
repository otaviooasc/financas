package com.api.financas.service;

import com.api.financas.domain.despesasfixas.DespesaFixa;
import com.api.financas.domain.usuario.Usuario;
import com.api.financas.dto.DespesaFixaRequestDTO;
import com.api.financas.dto.DespesaFixaResponseDTO;
import com.api.financas.exceptions.ReceitaException;
import com.api.financas.repositories.DespesaFixaRepository;
import com.api.financas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DespesaFixaService {

    @Autowired
    private DespesaFixaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DespesaFixa criar(String id, DespesaFixaRequestDTO dto) throws ReceitaException {
        var despesa = new DespesaFixa(dto);
        var usuario = new Usuario();

        usuario = usuarioRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ReceitaException("Nenhum usuário encontrado com esse id: " + id));

        despesa.setUsuario(usuario);
        repository.save(despesa);
        return despesa;
    }

    public List<DespesaFixaResponseDTO> listarTodos(String id) {
        UUID usuarioId = UUID.fromString(id);
        List<DespesaFixa> receitaList = repository.findByUsuarioId(usuarioId);

        if (receitaList.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com esse id: " + id);
        }

        return receitaList.stream()
                .map(DespesaFixaResponseDTO::new)
                .collect(Collectors.toList());
    }

}
