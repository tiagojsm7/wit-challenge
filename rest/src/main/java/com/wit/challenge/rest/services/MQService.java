package com.wit.challenge.rest.services;

import java.math.BigDecimal;

import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.wit.challenge.entities.OperationRequest;
import com.wit.challenge.entities.OperationResult;
import com.wit.challenge.entities.Operations;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.wit.challenge.entities.Constants;

@Service
public class MQService {

	private RabbitTemplate rabbitTemplate;
	private final Logger logger = LoggerFactory.getLogger(MQService.class);

	public MQService(RabbitTemplate rabbitTemplate) {
		super();
		if (rabbitTemplate == null) {
		    throw new IllegalArgumentException("rabbitTemplate cannot be null");
		}
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public OperationResult getCalculation(Operations operation, BigDecimal a, BigDecimal b) {
		OperationResult result = new OperationResult(null, "Something went wrong.");
		try {
			logger.info("Trying to calculate with params: {} {} {}", operation, a, b);
			result = (OperationResult) rabbitTemplate.convertSendAndReceive(Constants.QUEUE_NAME,
					new OperationRequest(operation,a,b));
		} catch (Exception e) {
			logger.error("Unable to get calculation, exception:", e);
		} finally {
			logger.info("Result: {}", result);
		}
		return result;
	}
}