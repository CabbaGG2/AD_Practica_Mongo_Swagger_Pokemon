package org.example.repository;

import org.example.model.Entrenador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntrenadorRepository extends MongoRepository<Entrenador, String> {
    List<Entrenador> findByName(String name);
}
