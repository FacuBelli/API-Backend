//Capa de trafico: se implementan los diferentes endpoints. Aca se ponen el get, post, put y demas. Entonces asi modularizas y solo tiene una responsabilidad, no hay logica de negocio, solo CRUD

package com.example.uade.tpo.ecommerce.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




//Con estod eclaro que esta clase es un endpoint HTTP
@RestController 


// Esto me define que url tiene que apuntar, a cada url que empiece con categoires, me trae todo lo que tiene ahi
@RequestMapping("categories") 
public String requestMethodName(@RequestParam String param) {
    return new String();
}




public class CategoryController {

    @GetMapping("path")  
    //Cuadno le pegue a una url con lo denominado arriba (categories), ejecuta lo que tenga adentro
    public String getCategories() {
        return new String();
    }

    @GetMapping("{/categoryId}")
    public String getCategoriesById(@PathVariable String categoryId) {
        return new String();
    }
    


    @PostMapping("path")
    public String createCategory(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    
}
