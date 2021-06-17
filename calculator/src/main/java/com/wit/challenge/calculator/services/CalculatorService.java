package com.wit.challenge.calculator.services;

import java.math.BigDecimal;
import java.math.MathContext;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.wit.challenge.entities.Constants;
import com.wit.challenge.entities.OperationRequest;
import com.wit.challenge.entities.OperationResult;

@Service
public class CalculatorService {
	
	Logger logger = LoggerFactory.getLogger(CalculatorService.class);
	
	@RabbitListener(queues = Constants.QUEUE_NAME)
	public OperationResult listenAMQP(OperationRequest request) {
		logger.info("Request received: {}", request);

		OperationResult result = calculate(request);

		logger.info("Request result: {}", result);

		return result;
	}
	
	private OperationResult calculate(OperationRequest request) {
		if (request.getFirstOperator() == null || request.getSecondOperator() == null) {
			return new OperationResult(null, "Arguments cannot be null.");
		}
		if (request.getOperation() == null) {
			return new OperationResult(null, "Invalid operation received.");
		}

		switch (request.getOperation()) {
		case DIVIDE:
			if (request.getSecondOperator().compareTo(BigDecimal.ZERO) == 0) {
				return new OperationResult(null, "Cannot divide by zero.");
			}
			return new OperationResult(request.getFirstOperator().divide(request.getSecondOperator(), MathContext.DECIMAL128), "Ok");
		case MULTIPLY:
			return new OperationResult(request.getFirstOperator().multiply(request.getSecondOperator()), "Ok");
		case SUBTRACT:
			return new OperationResult(request.getFirstOperator().subtract(request.getSecondOperator()), "Ok");
		case SUM:
			return new OperationResult(request.getFirstOperator().add(request.getSecondOperator()), "Ok");
		default:
			return new OperationResult(null, "Invalid operation received.");
		}
	}
	
}
