package com.wit.challenge.rest.services;

import java.math.BigDecimal;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wit.challenge.entities.OperationRequest;
import com.wit.challenge.entities.OperationResult;
import com.wit.challenge.entities.Operations;

@Service
public class MQService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public OperationResult getCalculation(Operations operation, BigDecimal a, BigDecimal b) {
		OperationResult result = new OperationResult(null, "Something went wrong.");
		try {
			result = (OperationResult) rabbitTemplate.convertSendAndReceive("calculationQueue", new OperationRequest(operation,a,b));			
		} catch (Exception e) {
			// TODO: handle exception 
			// logging
		}
		return result;
	}
}