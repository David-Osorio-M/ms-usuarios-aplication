package com.bci.onlinebanking.msusuariosaplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT) 
public class UsuarioAlreadyExistsException extends RuntimeException {

    public UsuarioAlreadyExistsException(String message) {
        super(message);
    }
}