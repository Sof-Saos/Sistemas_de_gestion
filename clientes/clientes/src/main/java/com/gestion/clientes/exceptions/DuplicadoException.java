package com.gestion.clientes.exceptions;

public class DuplicadoException extends RuntimeException {
    public DuplicadoException(String message) {
        super(message);
    }
}