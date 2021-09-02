package com.elektron.swapi.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo responsável pelos dados de planeta, mantidos pela API
 * @author Edmilson Pontes
 * @email edmilsonspontes@gmail.com 
 * @github https://github.com/edmilsonspontes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "planet")
@ApiModel(value = "Planet", description = "Dados do planeta")
public class Planet implements Serializable {

    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer numberAppearances;

}
