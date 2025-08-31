package com.pm.authservice.dto;

public class LoginResponseDTO {
    //since we have only 1 variable we are using constructor instead of setter
    //cannot be reinitialize again once already initialize
    private final String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
