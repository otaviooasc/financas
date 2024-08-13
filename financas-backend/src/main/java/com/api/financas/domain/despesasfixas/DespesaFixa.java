package com.api.financas.domain.despesasfixas;

import com.api.financas.domain.usuario.Usuario;
import com.api.financas.dto.despesafixa.DespesaFixaRequestDTO;
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
@Table(name = "despesa_fixa")
public class DespesaFixa {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private Double valor;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public DespesaFixa(DespesaFixaRequestDTO dto) {
        this.nome = dto.nome();
        this.valor = dto.valor();
        this.data = dto.data();
    }
}
