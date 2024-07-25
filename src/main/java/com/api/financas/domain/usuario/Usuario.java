package com.api.financas.domain.usuario;

import com.api.financas.domain.despesasfixas.DespesaFixa;
import com.api.financas.domain.despesavariavel.DespesaVariavel;
import com.api.financas.domain.receita.Receita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private String email;

    private String password;

    private LocalDate primeiroAcesso;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ArrayList<Receita> receitas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ArrayList<DespesaFixa> despesasFixas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private ArrayList<DespesaVariavel> despesaVariaveis;
}
