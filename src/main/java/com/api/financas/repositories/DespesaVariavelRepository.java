package com.api.financas.repositories;

import com.api.financas.domain.despesavariavel.DespesaVariavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DespesaVariavelRepository extends JpaRepository<DespesaVariavel, UUID> {
}
