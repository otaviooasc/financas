package com.api.financas.service;

import com.api.financas.domain.receita.Receita;
import com.api.financas.domain.usuario.Usuario;
import com.api.financas.dto.ReceitaDTO;
import com.api.financas.exceptions.ReceitaException;
import com.api.financas.repositories.ReceitaRespository;
import com.api.financas.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
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

    public Receita criar(String id, ReceitaDTO receitaDTO) throws ReceitaException {
        var receita = new Receita();
        var usuario = new Usuario();

        usuario = usuarioRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ReceitaException("Nenhum usuário encontrado com esse id: " + id));

        BeanUtils.copyProperties(receitaDTO, receita);
        receita.setUsuario(usuario);
        repository.save(receita);
        return receita;
    }

    public Receita alterar(String idReceita, ReceitaDTO receitaDTO) throws ReceitaException {
        var receita = new Receita();

        receita = repository.findById(UUID.fromString(idReceita))
                .orElseThrow(() -> new ReceitaException("Nenhum usuário encontrado com esse id: " + idReceita));

        BeanUtils.copyProperties(receitaDTO, receita);
        repository.save(receita);
        return receita;
    }

    public List<ReceitaDTO> listarTodos(String id) {
        List<Receita> receitaList = repository.findByUsuarioId(UUID.fromString(id));

        if (receitaList.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado com esse id: " + id);
        }

        return receitaList.stream()
                .map(ReceitaDTO::new)
                .collect(Collectors.toList());
    }

    public ReceitaDTO listarPorData(String id, LocalDate data) throws ReceitaException {
        Receita receita = repository.findByUsuarioIdAndData(UUID.fromString(id), data)
                .orElseThrow(() -> new ReceitaException("Nenhuma receita foi encontrada nessa data: " + data));
        return new ReceitaDTO(receita);
    }

    public void deletarReceita(String idReceita) {
        repository.deleteById(UUID.fromString(idReceita));
    }
}
