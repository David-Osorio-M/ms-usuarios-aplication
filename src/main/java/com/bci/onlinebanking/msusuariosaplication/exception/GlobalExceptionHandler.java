package com.bci.onlinebanking.msusuariosaplication.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo específico de UsuarioAlreadyExistsException
    @ExceptionHandler(UsuarioAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleUsuarioAlreadyExistsException(UsuarioAlreadyExistsException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    //     // Manejo específico de UsuarioAlreadyExistsException
    // @ExceptionHandler(ValidationException.class)
    // public ResponseEntity<ErrorDetails> ValidationException(UsuarioAlreadyExistsException ex, WebRequest request) {
    //     ErrorDetails errorDetails = new ErrorDetails(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getDescription(false));
    //     return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    // }

    // Manejo genérico de todas las demás excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
    @Data
    @AllArgsConstructor
    static class ErrorDetails {
        private int statusCode;
        private String message;
        private String details;
    }
}
