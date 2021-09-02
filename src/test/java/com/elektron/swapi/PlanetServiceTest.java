package com.elektron.swapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.elektron.swapi.model.Planet;
import com.elektron.swapi.service.PlanetService;
import com.elektron.swapi.util.PlanetUtil;

/**
 * Classe responsável pelos testes unitários das operações de PlanetService
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PlanetServiceTest {
	@Autowired
	private PlanetService service;

	@DisplayName("Test create planet")
	@Test
	void create() throws Exception {
		Planet planet = PlanetUtil.buildPlanet(null, "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		
		Planet planetResponse = service.create(planet);

		Assertions.assertNotNull(planetResponse.getId());
	    Assertions.assertEquals(planetResponse.getName(), planet.getName());
	    Assertions.assertEquals(planetResponse.getClimate(), planet.getClimate());
	    Assertions.assertEquals(planetResponse.getTerrain(), planet.getTerrain());
	}
	
	@DisplayName("Test update planet")
	@Test
	void update() throws Exception {
		Planet planet = PlanetUtil.buildPlanet(null, "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		planet = service.create(planet);
		
		String newClimate = planet.getClimate() + "_Patch";
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("name", planet.getName());
		requestMap.put("climate", newClimate);
		requestMap.put("terrain", planet.getTerrain());
		
		Planet planetResponse = service.update(planet.getId(), requestMap);

		Assertions.assertEquals(planetResponse.getId(), planet.getId());
	    Assertions.assertEquals(planetResponse.getName(), planet.getName());
	    Assertions.assertEquals(planetResponse.getClimate(), newClimate);
	    Assertions.assertEquals(planetResponse.getTerrain(), planet.getTerrain());
	}

	@DisplayName("Test find planet by id")
	@Test
	void findById() throws Exception {
		Planet planet = PlanetUtil.buildPlanet(null, "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		planet = service.create(planet);
		
		Planet planetResponse = service.findById(planet.getId());

		Assertions.assertEquals(planetResponse.getId(), planet.getId());
	}

	@DisplayName("Test find planet by name")
	@Test
	void findByName() throws Exception {
		Planet planet = PlanetUtil.buildPlanet(null, "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		planet = service.create(planet);
		
		List<Planet> planetResponse = service.findByName(planet.getName());

		Assertions.assertNotNull(planetResponse);
		Assertions.assertTrue(planetResponse.size() > 0);
	}
	
	@DisplayName("Test delete planet by id")
	@Test
	void delete() throws Exception {
		Planet planet = PlanetUtil.buildPlanet(null, "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		planet = service.create(planet);
		
		planet = service.create(planet);
		service.delete(planet.getId());
		try {
			service.findById(planet.getId());
		} catch (Exception e) {
			Assertions.assertEquals(e.getMessage(), "404 NOT_FOUND");
		}
	}

}
