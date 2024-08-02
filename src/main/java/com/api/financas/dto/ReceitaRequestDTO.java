package com.api.financas.dto;

import com.api.financas.domain.receita.Receita;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReceitaRequestDTO(@NotNull LocalDate data, @NotNull Double saldoLiquido, @NotNull Double rendimentoAluguel,
                                @NotNull Double rendimentoAplicacoes,
                                @NotNull Double outros) {
}
