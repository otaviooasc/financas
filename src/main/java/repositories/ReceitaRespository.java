package repositories;

import domain.receita.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReceitaRespository extends JpaRepository<Receita, UUID> {
}
