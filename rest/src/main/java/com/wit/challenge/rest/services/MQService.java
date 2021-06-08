package com.wit.challenge.rest.services;

import java.math.BigDecimal;

import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wit.challenge.entities.OperationRequest;
import com.wit.challenge.entities.OperationResult;
import com.wit.challenge.entities.Operations;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.wit.challenge.entities.Constants;


@Service
public class MQService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	Logger logger = LoggerFactory.getLogger(MQService.class);
	
	public OperationResult getCalculation(Operations operation, BigDecimal a, BigDecimal b) {
		OperationResult result = new OperationResult(null, "Something went wrong.");
		try {
			logger.info("Trying to calculate with params: {} {} {}", operation, a, b);
			result = (OperationResult) rabbitTemplate.convertSendAndReceive("calculationQueue",
					new OperationRequest(operation,a,b, MDC.get(Constants.DEFAULT_MDC_UUID_TOKEN_KEY)));
		} catch (Exception e) {
			logger.error("Unable to get calculation, exception:", e);
		} finally {
			logger.info("Result: {}", result);
		}
		return result;
	}
}