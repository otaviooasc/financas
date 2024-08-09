package com.api.financas.dto.despesavariavel;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DespesaVariavelRequestDTO(@NotNull String nome, @NotNull Double valor, @NotNull LocalDate data) {
}
