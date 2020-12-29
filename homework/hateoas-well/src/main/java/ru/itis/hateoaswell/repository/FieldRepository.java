package ru.itis.hateoaswell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoaswell.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
