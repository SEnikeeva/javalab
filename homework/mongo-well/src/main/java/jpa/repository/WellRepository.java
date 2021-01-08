package jpa.repository;

import jpa.model.Type;
import jpa.model.Well;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WellRepository extends MongoRepository<Well, String> {

    @Query(value = "{$or: [{'type': ?type}, {'name': {$regex: ?regexp}}]}")
    List<Well> find(@Param("type")Type type, @Param("regexp")String regexp);
}
