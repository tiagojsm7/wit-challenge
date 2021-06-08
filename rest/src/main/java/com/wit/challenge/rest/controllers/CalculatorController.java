package com.wit.challenge.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wit.challenge.entities.OperationResult;
import com.wit.challenge.entities.Operations;
import com.wit.challenge.rest.services.MQService;

@RestController("/")
public class CalculatorController {

	@Autowired
	private MQService mqService;

	@GetMapping(path = "{operation}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<OperationResult> getCalculation(@PathVariable(name = "operation", required = true) Operations operation, 
			@RequestParam(name = "a", required = true) BigDecimal a, @RequestParam(name = "b", required = true) BigDecimal b){

		return new ResponseEntity<OperationResult>(mqService.getCalculation(operation, a, b), HttpStatus.OK);
	}
}