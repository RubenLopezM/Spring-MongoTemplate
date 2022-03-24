package com.example.demo.Exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String mensaje){
        super(mensaje);
    }
}
