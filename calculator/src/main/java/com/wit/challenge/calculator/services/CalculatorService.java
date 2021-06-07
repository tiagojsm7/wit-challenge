package com.wit.challenge.calculator.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.wit.challenge.entities.OperationRequest;

@Service
public class CalculatorService {
	@RabbitListener(queues = "calculationQueue")
	public void listenAMQP(OperationRequest request) {
		System.out.println(request.toString());
	}
}
