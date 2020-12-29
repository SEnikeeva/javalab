package ru.itis.hateoaswell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoaswell.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
