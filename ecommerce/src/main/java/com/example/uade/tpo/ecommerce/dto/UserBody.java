package com.example.uade.tpo.ecommerce.dto;


import lombok.Data;


@Data
public class UserBody {
    private String biography;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private boolean is_artist;
}
