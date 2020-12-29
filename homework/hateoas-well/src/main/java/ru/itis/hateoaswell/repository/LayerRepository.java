package ru.itis.hateoaswell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoaswell.model.Layer;

public interface LayerRepository extends JpaRepository<Layer, Long> {
}
