package com.api.financas.dto.despesavariavel;

import com.api.financas.domain.despesavariavel.DespesaVariavel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DespesaVariavelResponseDTO(@NotNull UUID id, @NotNull String nome, @NotNull Double valor,
                                         @NotNull LocalDate data) {

    public DespesaVariavelResponseDTO(DespesaVariavel despesaVariavel) {
        this(despesaVariavel.getId(),
                despesaVariavel.getNome(),
                despesaVariavel.getValor(),
                despesaVariavel.getData()
        );
    }
}
