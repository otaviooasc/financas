package com.api.financas.repositories;

import com.api.financas.domain.receita.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReceitaRespository extends JpaRepository<Receita, UUID> {
    List<Receita> findByUsuarioIdOrderByDataDesc(UUID id);

    Optional<Receita> findByUsuarioIdAndData(UUID id, LocalDate data);
}
