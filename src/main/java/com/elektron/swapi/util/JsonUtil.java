package com.elektron.swapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utilitário responsável por operações envolvendo formato/objeto json 
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
public class JsonUtil {
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
}
