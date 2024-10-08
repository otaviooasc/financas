package com.api.financas.dto.despesafixa;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DespesaFixaRequestDTO(@NotNull String nome, @NotNull Double valor, @NotNull LocalDate data) {
}
