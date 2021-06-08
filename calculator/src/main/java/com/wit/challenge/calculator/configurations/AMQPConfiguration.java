package com.wit.challenge.calculator.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wit.challenge.entities.Constants;

@Configuration
public class AMQPConfiguration {

	@Bean
	Queue calculationQueue() {
	    return new Queue(Constants.QUEUE_NAME, false);
	}

}