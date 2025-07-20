package com.sistema.examenes.entidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String token;

    public JwtResponse(){

    }

    public JwtResponse(String token){
    this.token = token;

    }
}
 