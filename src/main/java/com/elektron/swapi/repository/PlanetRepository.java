package com.elektron.swapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.elektron.swapi.model.Planet;

/**
 * Repositório responsável pelas operações com o banco de dados local 
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {
	List<Planet> findByName(final String name);
}