package com.api.financas.service;

import com.api.financas.domain.despesasfixas.DespesaFixa;
import com.api.financas.domain.usuario.Usuario;
import com.api.financas.dto.DespesaFixaRequestDTO;
import com.api.financas.dto.DespesaFixaResponseDTO;
import com.api.financas.exceptions.GenericaException;
import com.api.financas.repositories.DespesaFixaRepository;
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
public class DespesaFixaService {

    @Autowired
    private DespesaFixaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DespesaFixa criar(String id, DespesaFixaRequestDTO dto) throws GenericaException {
        var despesa = new DespesaFixa(dto);
        var usuario = new Usuario();

        usuario = usuarioRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new GenericaException("Nenhum usuário encontrado com esse id: " + id));

        despesa.setUsuario(usuario);
        repository.save(despesa);
        return despesa;
    }

    public DespesaFixa alterar(String id, DespesaFixaRequestDTO dto) throws GenericaException {
        var despesaFixa = new DespesaFixa();

        despesaFixa = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new GenericaException("Nenhuma despesa fixa encontrada com esse id: " + id));

        despesaFixa.setData(dto.data());
        despesaFixa.setNome(dto.nome());
        despesaFixa.setValor(dto.valor());
        return despesaFixa;
    }

    public List<DespesaFixaResponseDTO> listarTodos(String id) throws GenericaException {
        UUID usuarioId = UUID.fromString(id);
        List<DespesaFixa> despesaFixaList = repository.findByUsuarioId(usuarioId);

        if (despesaFixaList.isEmpty()) {
            throw new GenericaException("Nenhum usuário encontrado com esse id: " + id);
        }

        return despesaFixaList.stream()
                .map(DespesaFixaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<DespesaFixaResponseDTO> listarPorData(String usuarioId, LocalDate data) throws GenericaException {
        List<DespesaFixa> despesaFixaList = repository.findByUsuarioIdAndData(UUID.fromString(usuarioId), data);

        if (despesaFixaList.isEmpty()) {
            throw new GenericaException("Nenhuma despesa fixa encontrada nessa data: " + data);
        }

        return despesaFixaList.stream()
                .map(DespesaFixaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void deletar(String despesaFixaId) throws GenericaException {
        var despesaFixa = repository.findById(UUID.fromString(despesaFixaId))
                .orElseThrow(() -> new GenericaException("Nenhuma despesa fixa encontrada com esse id: " + despesaFixaId));
        repository.deleteById(UUID.fromString(despesaFixaId));
    }
}
