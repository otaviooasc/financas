package domain.receita;

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
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
