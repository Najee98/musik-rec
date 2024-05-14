package com.musikrec.musikrec.Exceptions;

import com.musikrec.musikrec.Exceptions.CustomExceptions.AuthenticationException;
import com.musikrec.musikrec.Exceptions.CustomExceptions.DuplicatedResourceException;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e){
        return new ResponseEntity<>("Could not authenticate successfully.", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DuplicatedResourceException.class)
    public ResponseEntity<String> handleDuplicatedResourceException(DuplicatedResourceException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}