package com.api.financas.domain.receita;

import com.api.financas.domain.usuario.Usuario;
import com.api.financas.dto.receita.ReceitaRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "receita")
public class Receita {

    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate data;

    private Double saldoLiquido;

    private Double rendimentoAluguel;

    private Double rendimentoAplicacoes;

    private Double outros;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Receita(ReceitaRequestDTO receitaRequestDTO) {
        this.data = receitaRequestDTO.data();
        this.saldoLiquido = receitaRequestDTO.saldoLiquido();
        this.rendimentoAluguel = receitaRequestDTO.rendimentoAluguel();
        this.rendimentoAplicacoes = receitaRequestDTO.rendimentoAplicacoes();
        this.outros = receitaRequestDTO.outros();
    }
}
