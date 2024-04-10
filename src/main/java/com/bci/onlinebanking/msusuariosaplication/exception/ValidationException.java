package com.bci.onlinebanking.msusuariosaplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)//400
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}