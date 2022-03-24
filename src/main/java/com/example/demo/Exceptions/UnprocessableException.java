package com.example.demo.Exceptions;

public class UnprocessableException extends RuntimeException {
    public UnprocessableException(String mensaje) {
        super(mensaje);
    }
}
