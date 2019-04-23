package br.com.api.timesheet.repository;

import br.com.api.timesheet.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Transactional(readOnly = true)
    Optional<Position> findByTitle(String title);
}
