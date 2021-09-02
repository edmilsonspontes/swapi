package com.elektron.swapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elektron.swapi.model.Planet;
import com.elektron.swapi.model.PlanetRequest;
import com.elektron.swapi.service.PlanetService;
import com.elektron.swapi.util.PlanetUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador responsável pelas requisições das operações de cadastro de planetas 
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/planets")
@Api(value = "Operações com planetas", description = "Realiza operações com planetas")
public class PlanetController {

	@Autowired
	private final PlanetService planetService;
	
	
	@ApiOperation(value = "Busca planeta na base de dados local, por nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Planeta não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
	@GetMapping( value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findPlanetByName(@PathVariable(value = "name") String pName) {
		try {
			List<Planet> planetResult = planetService.findByName(pName);
        	if(planetResult == null || planetResult.size() == 0) {
        		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        	}
        	planetResult.forEach(planet -> {
        		Integer numberFilms = planetService.findNumberFilmsByNameSwapi(planet.getName());
        		PlanetUtil.setNumberFilms(planet, numberFilms);
        	});
            return ResponseEntity.ok().body(planetResult);
		}
        catch (Exception ex){
            log.error("error when searching planets ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
    @ApiOperation(value = "Busca planeta na base de dados local, por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Planeta não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable(value = "id") String pId ){
        try{
        	Planet planet = planetService.findById(pId);
        	if(planet == null) {
        		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        	}
    		Integer numberFilms = planetService.findNumberFilmsByNameSwapi(planet.getName());
            PlanetUtil.setNumberFilms(planet, numberFilms);
            return ResponseEntity.ok().body(planet);
        }
        catch (Exception ex){
            log.error("error when searching planets ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Busca lista de todos os planetas cadastrados na base de dados local")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Planeta não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAll(){
        try{
            List<Planet> planetList  = planetService.findAll();
            
        	if(planetList != null && planetList.size() > 0) {
            	final List<Planet> result = new ArrayList();
            	planetList.forEach(planet -> {
            		Integer numberFilms = planetService.findNumberFilmsByNameSwapi(planet.getName());
                    PlanetUtil.setNumberFilms(planet, numberFilms);
        			result.add(planet);
            	});
            	return ResponseEntity.ok().body(result);
            }
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception ex){
            log.error("error when searching planets ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Cria um novo planeta na base de dados local")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 204, message = "Operação não realizada"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody PlanetRequest request){
        try{
        	Planet planetRequest = PlanetUtil.toPlanetEntity(request);
            final Planet planet = planetService.create(planetRequest);
            if(planet == null) {
            	return ResponseEntity.status(204).build();
            }
            
            Integer numberFilms = planetService.findNumberFilmsByNameSwapi(planet.getName());
            PlanetUtil.setNumberFilms(planet, numberFilms);
            return Optional.ofNullable(planet)
                    .map(x -> ResponseEntity.status(HttpStatus.CREATED).body(planet))
                    .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        }
        catch (Exception ex){
            log.error("Error creating planet ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Atualiza dados de um planeta na base de dados local")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 204, message = "Operação não realizada"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @PatchMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable(value = "id") String pId, @RequestBody Map requestMap){
        try{
        	
        	Planet planet = planetService.update(pId, requestMap);
            Integer numberFilms = planetService.findNumberFilmsByNameSwapi(planet.getName());
            PlanetUtil.setNumberFilms(planet, numberFilms);
            
            return Optional.ofNullable(planet)
                    .map(x -> ResponseEntity.status(HttpStatus.ACCEPTED).body(planet))
                    .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        }
        catch (Exception ex){
            log.error("Error updating planet ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    @ApiOperation(value = "Exclui um planeta da base de dados local")
    @ApiResponses(value = {
            @ApiResponse(code = 410, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 204, message = "Operação não realizada"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @DeleteMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable(value = "id") String pId){
        try{
            planetService.delete(pId);
            return ResponseEntity.status(HttpStatus.GONE).build();
        }
        catch (Exception ex){
            log.error("Error deleting planet ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @ApiOperation(value = "Exclui TODOS os planetas da base de dados local")
    @ApiResponses(value = {
            @ApiResponse(code = 410, message = "Operação realizada com sucesso"),
            @ApiResponse(code = 204, message = "Operação não realizada"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
    @PostMapping( value = "/deleteAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAll(){
        try{
        	List<Planet> planetList  = planetService.findAll();
        	if(planetList != null && planetList.size() > 0) {
        		planetList.forEach(planet -> {
        			try {
						planetService.delete(planet.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
        		});
        	}
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception ex){
            log.error("Error deleting planet ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
