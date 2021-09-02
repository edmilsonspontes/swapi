package com.elektron.swapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.elektron.swapi.model.SwapiClientResponse;

/**
 * Client responsável pela integração com a API https://swapi.dev
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@Component
@FeignClient(value = "StarWarsAPI", url = "${integration.swapi:https://swapi.dev/api/planets}")
public interface SwapiWebClient {
	
	@GetMapping(value = "/")
	SwapiClientResponse findByName(@RequestParam("search") String planetName);
	
}
