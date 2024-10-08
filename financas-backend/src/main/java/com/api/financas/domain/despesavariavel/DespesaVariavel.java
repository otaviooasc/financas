package com.api.financas.domain.despesavariavel;

import com.api.financas.domain.usuario.Usuario;
import com.api.financas.dto.despesavariavel.DespesaVariavelRequestDTO;
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
@Table(name = "despesa_variavel")
public class DespesaVariavel {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private Double valor;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public DespesaVariavel(DespesaVariavelRequestDTO dto) {
        this.nome = dto.nome();
        this.valor = dto.valor();
        this.data = dto.data();
    }
}
