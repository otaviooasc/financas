package com.api.financas.dto.despesafixa;

import com.api.financas.domain.despesasfixas.DespesaFixa;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record DespesaFixaResponseDTO(@NotNull UUID id, @NotNull String nome, @NotNull Double valor,
                                     @NotNull LocalDate data) {

    public DespesaFixaResponseDTO(DespesaFixa despesaFixa) {
        this(despesaFixa.getId(),
                despesaFixa.getNome(),
                despesaFixa.getValor(),
                despesaFixa.getData()
        );
    }
}
