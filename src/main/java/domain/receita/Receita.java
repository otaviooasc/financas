package domain.receita;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Double saldoLiquido;

    private Double rendimentoAluguel;

    private Double rendimentoAplicacoes;

    private Double outros;
}
