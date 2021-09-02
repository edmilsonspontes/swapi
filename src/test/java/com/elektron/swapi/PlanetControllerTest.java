package com.elektron.swapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.elektron.swapi.model.Planet;
import com.elektron.swapi.util.JsonUtil;
import com.elektron.swapi.util.PlanetUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe responsável pelos testes unitários dos endpoints de PlanetController 
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PlanetControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@DisplayName("Test create planet")
	@Test
	void create() throws Exception {
		Planet planet = PlanetUtil.buildPlanet(null, "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/planets")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(planet)))
		        .andExpect(status().isCreated());
	}
	
	@DisplayName("Test update planet")
	@Test
	void update() throws Exception {
		Planet planet = PlanetUtil.buildPlanet("1", "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		Map<String, Object> planetMap = objectMapper.convertValue(planet, Map.class);
		mockMvc.perform( MockMvcRequestBuilders
			      .patch("/planets/{id}", 1)
			      .content(JsonUtil.asJsonString(planetMap))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Planet_01"))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.climate").value("Planet_01_Climate"))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.terrain").value("Planet_01_Terrain"));
	}

	@DisplayName("Test find planet by id")
	@Test
	void findById() throws Exception {
		Planet planet = PlanetUtil.buildPlanet(null, "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/planets/{id}", planet.getId())
		        .contentType("application/json"))
		        .andExpect(status().isOk());
	}

	@DisplayName("Test find planet by name")
	@Test
	void findByName() throws Exception {
		Planet planet = PlanetUtil.buildPlanet(null, "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		
		mockMvc.perform(get("/planets/name/{name}", planet.getName())
		        .contentType("application/json"))
		        .andExpect(status().isOk());
	}
	
	@DisplayName("Test delete planet by id")
	@Test
	void delete() throws Exception {
		Planet planet = PlanetUtil.buildPlanet(null, "Planet_01", "Planet_01_Climate", "Planet_01_Terrain");
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/planets/{id}", planet.getId())
		        .contentType("application/json"))
		        .andExpect(status().isOk());
	}

}
