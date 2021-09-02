package com.elektron.swapi.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * Modelo responsável pelos dados de requisições de clients da API
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@Data
@Builder
@ApiModel(value = "PlanetRequest", description = "Dados para requisição de operações com planeta")
public class PlanetRequest {
    PlanetRequest(){}

    PlanetRequest(String name, String climate, String terrain){
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    @NotNull (message = "Property name is required.")
    @NotEmpty (message = "Property name is required.")
    private String name;
    
    @NotNull (message = "Property climate is required.")
    @NotEmpty (message = "Property climate is required.")
    private String climate;
    
    @NotNull (message = "Property terrain is required.")
    @NotEmpty (message = "Property terrain is required.")
    private String terrain;

}
