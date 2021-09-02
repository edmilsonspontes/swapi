package com.elektron.swapi.util;

import com.elektron.swapi.model.Planet;
import com.elektron.swapi.model.PlanetRequest;
import com.elektron.swapi.model.PlanetResponse;

/**
 * Utilitário responsável por operações genéricas de Planeta 
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
public class PlanetUtil {
	
	public static Planet buildPlanet(String pId, String pName, String pClimate, String pTerrain) {
		Planet planet = Planet
				.builder()
				.id(pId)
				.name(pName)
				.climate(pClimate)
				.terrain(pTerrain)
				.build();
		return planet;
	}
	
	public static PlanetResponse toPlanetResponse(Planet planet) {
		return PlanetResponse
				.builder()
				.id(planet.getId())
				.name(planet.getName())
				.climate(planet.getClimate())
				.terrain(planet.getTerrain())
				.build();
	}

	public static Planet toPlanetEntity(PlanetRequest request) {
		return Planet
				.builder()
				.name(request.getName())
				.climate(request.getClimate())
				.terrain(request.getTerrain())
				.build();
	}
	
	
	public static void setNumberFilms(Planet planet, Integer pNumber) {
		if(planet != null) {
			planet.setNumberAppearances(pNumber);
		}
	}
	
}
