package ru.itis.hateoaswell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoaswell.model.Horizon;

public interface HorizonRepository extends JpaRepository<Horizon, Long> {
}
