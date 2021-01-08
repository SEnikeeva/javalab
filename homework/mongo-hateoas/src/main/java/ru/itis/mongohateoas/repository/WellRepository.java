package ru.itis.mongohateoas.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.mongohateoas.model.Type;
import ru.itis.mongohateoas.model.Well;

import java.awt.print.Pageable;
import java.util.List;

public interface WellRepository extends MongoRepository<Well, String> {

    @RestResource(path = "withtype", rel = "withtype")
    @Query(value = "{$or: [{'type': ?0}, {'name': {$regex: ?1}}]}")
    List<Well> find(@Param("type") Type type, @Param("regexp") String regexp, Pageable pageable);
}
