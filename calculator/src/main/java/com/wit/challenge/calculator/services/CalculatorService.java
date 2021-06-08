package com.wit.challenge.calculator.services;

import java.math.BigDecimal;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.wit.challenge.entities.OperationRequest;
import com.wit.challenge.entities.OperationResult;

@Service
public class CalculatorService {
	@RabbitListener(queues = "calculationQueue")
	public OperationResult listenAMQP(OperationRequest request) {
		return calculate(request);
	}
	
	private OperationResult calculate(OperationRequest request) {
		System.out.println(request.getOp());
		if (request.getA() == null || request.getB() == null) {
			return new OperationResult(null, "Arguments cannot be null.");
		}
		if (request.getOp() == null) {
			return new OperationResult(null, "Invalid operation received.");
		}

		switch (request.getOp()) {
		case DIV:
			if (request.getB().compareTo(BigDecimal.ZERO) == 0) {
				return new OperationResult(null, "Cannot divide by zero.");
			}
			return new OperationResult(request.getA().divide(request.getB()), "Ok");
		case MULTI:
			return new OperationResult(request.getA().multiply(request.getB()), "Ok");
		case SUBTR:
			return new OperationResult(request.getA().subtract(request.getB()), "Ok");
		case SUM:
			return new OperationResult(request.getA().add(request.getB()), "Ok");
		default:
			return new OperationResult(null, "Invalid operation received.");
		}
	}
	
}
