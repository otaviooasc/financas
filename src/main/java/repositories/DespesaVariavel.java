package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DespesaVariavel extends JpaRepository<DespesaVariavel, UUID> {
}
