package domain.despesavariavel;

import domain.usuario.Usuario;
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
@Table(name = "despesas-variavel")
public class DespesaVariavel {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private String valor;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
