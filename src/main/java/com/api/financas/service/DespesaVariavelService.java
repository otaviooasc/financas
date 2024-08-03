package com.api.financas.service;

import com.api.financas.domain.despesavariavel.DespesaVariavel;
import com.api.financas.domain.usuario.Usuario;
import com.api.financas.dto.DespesaVariavelRequestDTO;
import com.api.financas.dto.DespesaVariavelResponseDTO;
import com.api.financas.exceptions.GenericaException;
import com.api.financas.repositories.DespesaVariavelRepository;
import com.api.financas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DespesaVariavelService {

    @Autowired
    private DespesaVariavelRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DespesaVariavel criar(String id, DespesaVariavelRequestDTO dto) throws GenericaException {
        var despesa = new DespesaVariavel(dto);
        var usuario = new Usuario();

        usuario = usuarioRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new GenericaException("Nenhum usuário encontrado com esse id: " + id));

        despesa.setUsuario(usuario);
        repository.save(despesa);
        return despesa;
    }

    public DespesaVariavel alterar(String id, DespesaVariavelRequestDTO dto) throws GenericaException {
        var despesaVariavel = new DespesaVariavel();

        despesaVariavel = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new GenericaException("Nenhuma despesa variavel encontrada com esse id: " + id));

        despesaVariavel.setData(dto.data());
        despesaVariavel.setNome(dto.nome());
        despesaVariavel.setValor(dto.valor());
        return despesaVariavel;
    }

    public List<DespesaVariavelResponseDTO> listarTodos(String id) throws GenericaException {
        UUID usuarioId = UUID.fromString(id);
        List<DespesaVariavel> despesaVariavelList = repository.findByUsuarioId(usuarioId);

        if (despesaVariavelList.isEmpty()) {
            throw new GenericaException("Nenhum usuário encontrado com esse id: " + id);
        }

        return despesaVariavelList.stream()
                .map(DespesaVariavelResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<DespesaVariavelResponseDTO> listarPorData(String usuarioId, LocalDate data) throws GenericaException {
        List<DespesaVariavel> despesaVariavelList = repository.findByUsuarioIdAndData(UUID.fromString(usuarioId), data);

        if (despesaVariavelList.isEmpty()) {
            throw new GenericaException("Nenhuma despesa variavel encontrada nessa data: " + data);
        }

        return despesaVariavelList.stream()
                .map(DespesaVariavelResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void deletar(String despesaVariavelId) throws GenericaException {
        var despesaVariavel = repository.findById(UUID.fromString(despesaVariavelId))
                .orElseThrow(() -> new GenericaException("Nenhuma despesa variavel encontrada com esse id: " + despesaVariavelId));
        repository.deleteById(UUID.fromString(despesaVariavelId));
    }
}
