package ru.itis.hateoaswell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoaswell.model.Well;

import java.util.List;


@RepositoryRestResource
public interface WellRepository extends PagingAndSortingRepository<Well, Long> {
    List<Well> findAllByField(@Param("field") String field);
}
