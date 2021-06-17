package com.wit.challenge.calculator.configurations;

import javax.annotation.PostConstruct;

import org.slf4j.MDC;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.AbstractRabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wit.challenge.entities.Constants;

@Configuration
public class AMQPConfiguration {

	private AbstractRabbitListenerContainerFactory<?> abstractRabbitListenerContainerFactory;

	public AMQPConfiguration(AbstractRabbitListenerContainerFactory<?> abstractRabbitListenerContainerFactory) {
		super();
		if (abstractRabbitListenerContainerFactory == null) {
		    throw new IllegalArgumentException("abstractRabbitListenerContainerFactory cannot be null");
		}
		this.abstractRabbitListenerContainerFactory = abstractRabbitListenerContainerFactory;
	}

	@PostConstruct
	private void initMessagePostProcessors() {
		abstractRabbitListenerContainerFactory.setAfterReceivePostProcessors(m -> {
			String uuid = m.getMessageProperties().getHeader(Constants.DEFAULT_RABBIT_TOKEN_HEADER);
			MDC.put(Constants.DEFAULT_MDC_UUID_TOKEN_KEY, uuid);
			return m;
		});
	}

	@Bean
	Queue calculationQueue() {
		return new Queue(Constants.QUEUE_NAME, false);
	}

}