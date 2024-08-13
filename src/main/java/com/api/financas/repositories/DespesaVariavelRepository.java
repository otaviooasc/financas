package com.api.financas.repositories;

import com.api.financas.domain.despesavariavel.DespesaVariavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface DespesaVariavelRepository extends JpaRepository<DespesaVariavel, UUID> {
    List<DespesaVariavel> findByUsuarioId(UUID usuarioId);

    List<DespesaVariavel> findByUsuarioIdAndData(UUID id, LocalDate data);
}
