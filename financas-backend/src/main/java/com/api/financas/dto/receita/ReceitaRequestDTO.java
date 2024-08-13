package com.api.financas.dto.receita;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReceitaRequestDTO(@NotNull LocalDate data, @NotNull Double saldoLiquido,
                                @NotNull Double rendimentoAluguel,
                                @NotNull Double rendimentoAplicacoes,
                                @NotNull Double outros) {
}
