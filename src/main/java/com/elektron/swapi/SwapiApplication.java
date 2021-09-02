package com.elektron.swapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Aplicação responsável por manter cadastro de planetas e pela integração com a API https://swapi.dev
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@SpringBootApplication
@EnableFeignClients
public class SwapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwapiApplication.class, args);
	}

}
