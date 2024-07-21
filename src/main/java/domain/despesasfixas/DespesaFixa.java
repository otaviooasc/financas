package domain.despesasfixas;

import domain.usuario.Usuario;
import jakarta.persistence.*;
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
@Table(name = "despesas-fixas")
public class DespesaFixa {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private String valor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
