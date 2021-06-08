package com.wit.challenge.rest.controllers;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wit.challenge.entities.OperationResult;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<OperationResult> handleConflict(ConversionFailedException ex) {
        return new ResponseEntity<>(new OperationResult(null, "Invalid operation received."), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<OperationResult> handleConflict(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(new OperationResult(null, "Missing parameter: " + ex.getParameterName()), HttpStatus.BAD_REQUEST);
    }
}