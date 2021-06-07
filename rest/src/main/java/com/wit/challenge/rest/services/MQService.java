package com.wit.challenge.rest.services;

import java.math.BigDecimal;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public BigDecimal getCalculation(String operation, BigDecimal a, BigDecimal b) {

		rabbitTemplate.convertAndSend("calculationQueue", "HELLO WORLD");

		return new BigDecimal(-1);
	}
}