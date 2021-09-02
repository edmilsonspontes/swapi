package com.elektron.swapi.model;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Modelo responsável pelos dados obtidos da API https://swapi.dev/api
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@Data
@Builder
public class SwapiClientResponse implements Serializable {

    private int count;
    private List<PlanetResponse> results;
}
