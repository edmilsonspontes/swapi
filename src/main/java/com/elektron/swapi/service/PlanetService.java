package com.elektron.swapi.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elektron.swapi.client.SwapiWebClient;
import com.elektron.swapi.model.Planet;
import com.elektron.swapi.model.PlanetResponse;
import com.elektron.swapi.model.SwapiClientResponse;
import com.elektron.swapi.repository.PlanetRepository;

/**
 * Service responsável pelas regras de negócio das operações de cadastro de planetas 
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@Service
public class PlanetService {

	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private SwapiWebClient swapiWebClient;

	public PlanetResponse findByNameSwapi(String pName) {
		PlanetResponse result = null;
		SwapiClientResponse swapiResult = swapiWebClient.findByName(pName);

		if (swapiResult != null && swapiResult.getResults() != null && swapiResult.getResults().size() > 0) {
			result = swapiResult.getResults().get(0);
		}
		return result;
	}

	public List<String> findFilmsByNameSwapi(String pName) {
		PlanetResponse planetResponse = findByNameSwapi(pName);
		if (planetResponse != null && planetResponse.getFilms() != null && planetResponse.getFilms().size() > 0) {
			return planetResponse.getFilms();
		}
		return null;
	}

	public Integer findNumberFilmsByNameSwapi(String pName) {
		List<String> films = findFilmsByNameSwapi(pName);
		return (films != null && films.size() > 0) ? films.size() : Integer.valueOf(0);
	}

	public Planet findById(String pId) throws Exception {
		Optional<Planet> optionalResult = null;
		try {
			optionalResult = planetRepository.findById(pId);
		} catch (Exception ex) {
			throw new Exception(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}

		if (optionalResult == null || !optionalResult.isPresent()) {
			throw new Exception(HttpStatus.NOT_FOUND.toString());
		}
		return optionalResult.get();
	}

	public List<Planet> findByName(String name) throws Exception {
		List<Planet> planetResult = null;
		try {
			planetResult = planetRepository.findByName(name);
		} catch (Exception ex) {
			throw new Exception(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}

		if (planetResult == null || planetResult.size() == 0) {
			throw new Exception(HttpStatus.NOT_FOUND.toString());
		}
		return planetResult;
	}

	public List<Planet> findAll() throws Exception {
		List<Planet> resultList = null;
		try {
			resultList = planetRepository.findAll();
		} catch (Exception ex) {
			throw new Exception(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
		
		if (resultList == null || resultList.size() == 0) {
			throw new Exception(HttpStatus.NOT_FOUND.toString());
		}
		return resultList;
	}

	public Planet create(Planet planet) throws Exception {
		try {
			return planetRepository.save(planet);
		} catch (Exception ex) {
			throw new Exception(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
	}

	public Planet update(String pId, Map<String, Object> requestMap) throws Exception {

		try {
			Optional<Planet> planetDataBase = planetRepository.findById(pId);
			if (planetDataBase == null || !planetDataBase.isPresent()) {
				throw new Exception(HttpStatus.NOT_FOUND.toString());
			}

			Planet planetpatch = planetDataBase.get();
			Object name = requestMap.get("name");
			Object climate = requestMap.get("climate");
			Object terrain = requestMap.get("terrain");

			if (name != null) {
				planetpatch.setName(name.toString());
			}
			if (climate != null) {
				planetpatch.setClimate(climate.toString());
			}
			if (terrain != null) {
				planetpatch.setTerrain(terrain.toString());
			}
			return planetRepository.save(planetpatch);
		} catch (Exception ex) {
			throw new Exception(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
	}

	public void delete(String pId) throws Exception {
		if (!isExistingById(pId)) {
			throw new Exception(HttpStatus.NOT_FOUND.toString());
		}
		try {
			Optional<Planet> planet = planetRepository.findById(pId);
			planetRepository.delete(planet.get());
		} catch (Exception e) {
			throw new Exception(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
	}

	public boolean isExistingById(String pId) {
		Optional<Planet> planetOptional = planetRepository.findById(pId);
		if (planetOptional != null && planetOptional.isPresent() && planetOptional.get() != null) {
			return true;
		}
		return false;
	}

	public boolean isExistingByName(String pName) {
		Object planet = planetRepository.findByName(pName);
		if (planet != null) {
			return true;
		}
		return false;
	}

}
