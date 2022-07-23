package com.nozama.api.exception;

import com.nozama.api.dto.base.IdiomaDto;
import com.nozama.api.entity.Idioma;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ExceptionResponse buildExceptionResponseObject(String message, HttpStatus status, HttpServletRequest request) {
        return new ExceptionResponse(message, status.value(), request.getRequestURI());
    }
    private ExceptionResponse buildExceptionResponseObject(String message, HttpStatus status, HttpServletRequest request, Set<ConstraintViolation<?>> violations) {
        return new ExceptionResponse(message, status.value(), request.getRequestURI(), violations);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementException exception, HttpServletRequest request) {
        var message = "Não foi possível encontrar o recurso solicitado.";
        return ResponseEntity.status(404).body(buildExceptionResponseObject(message, HttpStatus.NOT_FOUND, request));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request) {
        var message = "Erro de validação.";
        return ResponseEntity.status(400).body(buildExceptionResponseObject(message, HttpStatus.BAD_REQUEST, request, exception.getConstraintViolations()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception, HttpServletRequest request) {

        var message = "Não é possível executar essa ação, pois outro recurso depende do recurso que está sendo alterado.";
        var messagesByHttpMethod = new HashMap<String, String>();
        var currentRequestHttpMethod = request.getMethod();

        messagesByHttpMethod.put("DELETE", "Não é possível deletar esse recurso pois um ou mais recursos dependem deste.");

        if(messagesByHttpMethod.containsKey(currentRequestHttpMethod)) message = messagesByHttpMethod.get(currentRequestHttpMethod);

        return ResponseEntity.status(400).body(buildExceptionResponseObject(message, HttpStatus.BAD_REQUEST, request));
    }

}
