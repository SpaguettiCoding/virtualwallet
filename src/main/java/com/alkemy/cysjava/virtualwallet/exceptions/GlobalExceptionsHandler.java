package com.alkemy.cysjava.virtualwallet.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {

    //Exception cuando no se encuentra un recurso
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleConflict(
            ResourceNotFoundException ex, WebRequest request
    ){
        return new ResponseEntity<>(new ApiException(Collections.singletonList(ex.getMessage())), HttpStatus.NOT_FOUND);
    }

    // Exception cuando la entidad ya existe en la base de datos
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleUserAlreadyExists(
            BadRequestException ex, WebRequest request
    ){
        return new ResponseEntity<>(new ApiException(Collections.singletonList(ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

    //Exception cuando los argumentos de la petición son incorrectos
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        List<String> messages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(m -> m.toString())
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ApiException(messages), HttpStatus.BAD_REQUEST);
    }
    
}
