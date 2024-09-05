package com.api.financas.service;

import com.api.financas.domain.receita.Receita;
import com.api.financas.domain.usuario.Usuario;
import com.api.financas.dto.receita.ReceitaRequestDTO;
import com.api.financas.dto.receita.ReceitaResponseDTO;
import com.api.financas.exceptions.GenericaException;
import com.api.financas.exceptions.NaoFoiEncontradoException;
import com.api.financas.repositories.ReceitaRespository;
import com.api.financas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRespository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Receita criar(String id, ReceitaRequestDTO receitaRequestDTO) throws GenericaException {
        var receita = new Receita(receitaRequestDTO);
        var usuario = new Usuario();

        usuario = usuarioRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NaoFoiEncontradoException("Nenhum usuário encontrado com esse id: " + id));

        receita.setUsuario(usuario);
        repository.save(receita);
        return receita;
    }

    public Receita alterar(String id, ReceitaRequestDTO receitaRequestDTO) throws GenericaException {
        UUID receitaId = UUID.fromString(id);
        var receita = new Receita();

        receita = repository.findById(receitaId)
                .orElseThrow(() -> new NaoFoiEncontradoException("Nenhuma receita encontrado com esse id: " + receitaId));

        receita.setData(receitaRequestDTO.data());
        receita.setSaldoLiquido(receitaRequestDTO.saldoLiquido());
        receita.setRendimentoAluguel(receitaRequestDTO.rendimentoAluguel());
        receita.setRendimentoAplicacoes(receitaRequestDTO.rendimentoAplicacoes());
        receita.setOutros(receitaRequestDTO.outros());
        repository.save(receita);
        return receita;
    }

    public List<ReceitaResponseDTO> listarTodos(String id) {
        UUID usuarioId = UUID.fromString(id);
        List<Receita> receitaList = repository.findByUsuarioIdOrderByDataDesc(usuarioId);

        if (receitaList.isEmpty()) {
            throw new NaoFoiEncontradoException("Nenhum usuário encontrado com esse id: " + id);
        }

        return receitaList.stream()
                .map(ReceitaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ReceitaResponseDTO listarPorData(String id, LocalDate data) throws GenericaException {
        Receita receita = repository.findByUsuarioIdAndData(UUID.fromString(id), data)
                .orElseThrow(() -> new NaoFoiEncontradoException("Nenhuma receita foi encontrada nessa data: " + data));
        return new ReceitaResponseDTO(receita);
    }

    public void deletar(String idReceita) {
        var receita = repository.findById(UUID.fromString(idReceita))
                .orElseThrow(() -> new NaoFoiEncontradoException("Nenhuma receita encontrado com esse id: " + idReceita));
        repository.deleteById(UUID.fromString(idReceita));
    }

    public ReceitaResponseDTO ListarPorId(String id) {
        return new ReceitaResponseDTO(repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NaoFoiEncontradoException("Nenhuma receita encontrado com esse id: " + id)));
    }

}
