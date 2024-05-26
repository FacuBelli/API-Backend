package com.example.uade.tpo.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity //esto dice que esta clase es una entidad eprsistida en una DDBB

public class Category {
    public Category() {
    }
    
    public Category(String description){
        this.description = description;
    }

    @Id //define que lo que esta abajo es la PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column //Esto mapea la propiedad para que sea parte del modelo de datos, osea la hace una columna
    private String description;

}

