package com.gestion.clientes.exceptions;

public class DatosInvalidosException extends RuntimeException{
    public DatosInvalidosException(String message) {
        super(message);
    }
}