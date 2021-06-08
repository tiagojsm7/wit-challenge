package com.wit.challenge.calculator.services;

import java.math.BigDecimal;
import java.math.MathContext;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wit.challenge.entities.Constants;
import com.wit.challenge.entities.OperationRequest;
import com.wit.challenge.entities.OperationResult;

@Service
public class CalculatorService {
	
	Logger logger = LoggerFactory.getLogger(CalculatorService.class);
	
	@RabbitListener(queues = Constants.QUEUE_NAME)
	public OperationResult listenAMQP(OperationRequest request) {
		if (request != null && !StringUtils.isEmpty(request.getUuid())) {
			MDC.put(Constants.DEFAULT_MDC_UUID_TOKEN_KEY, request.getUuid());
		}
		logger.info("Request received: {}", request);

		OperationResult result = calculate(request);

		logger.info("Request result: {}", result);

		return result;
	}
	
	private OperationResult calculate(OperationRequest request) {
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
			return new OperationResult(request.getA().divide(request.getB(), MathContext.DECIMAL128), "Ok");
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
