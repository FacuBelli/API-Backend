package com.example.uade.tpo.ecommerce.dto.body;


import lombok.Data;


@Data
public class UserBody {
    private String biography;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isArtist;
}
