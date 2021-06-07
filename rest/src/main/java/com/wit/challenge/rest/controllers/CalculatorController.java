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

import com.wit.challenge.rest.services.MQService;

@RestController("/")
public class CalculatorController {

	@Autowired
	private MQService mqService;

	@GetMapping(path = "{operation}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Object> getCalculation(@PathVariable(name = "operation", required = true) String operation, 
			@RequestParam(name = "a", required = true) BigDecimal a, @RequestParam(name = "b", required = true) BigDecimal b){


		return new ResponseEntity<Object>(mqService.getCalculation(operation, a, b), HttpStatus.OK);
	}
}