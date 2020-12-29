package ru.itis.hateoaswell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoaswell.model.Method;

public interface MethodRepository extends JpaRepository<Method, Long> {
}
