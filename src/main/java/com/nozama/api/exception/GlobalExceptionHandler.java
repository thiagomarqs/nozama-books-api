package com.nozama.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ExceptionResponse buildExceptionResponseObject(String message, HttpStatus status, HttpServletRequest request) {
        return new ExceptionResponse(message, status.value(), request.getRequestURI());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementException exception, HttpServletRequest request) {
        String message = "Não encontrado.";
        return ResponseEntity.status(404).body(buildExceptionResponseObject(message, HttpStatus.NOT_FOUND, request));
    }

}
