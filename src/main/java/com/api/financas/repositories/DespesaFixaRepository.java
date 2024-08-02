package com.api.financas.repositories;

import com.api.financas.domain.despesasfixas.DespesaFixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DespesaFixaRepository extends JpaRepository<DespesaFixa, UUID> {
    List<DespesaFixa> findByUsuarioId(UUID id);
}
