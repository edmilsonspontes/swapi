package com.elektron.swapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * Modelo responsável pelos dados de planetas obtidos da API https://swapi.dev/api
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@Data
@Builder
@ApiModel(value = "PlanetResponse", description = "Dados para retorno de operações com planeta")
public class PlanetResponse {
	private String id;
	private String name;
	private String terrain;
	private String climate;
	private List<String> films;
}
