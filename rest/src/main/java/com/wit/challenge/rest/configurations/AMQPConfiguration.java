package com.wit.challenge.rest.configurations;

import javax.annotation.PostConstruct;

import org.slf4j.MDC;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wit.challenge.entities.Constants;

import ch.qos.logback.access.tomcat.LogbackValve;

@Configuration
public class AMQPConfiguration {
	
	private RabbitTemplate rabbitTemplate;
	
	public AMQPConfiguration(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@PostConstruct
	private void initMessagePostProcessors() {
		rabbitTemplate.setBeforePublishPostProcessors(m -> {
				m.getMessageProperties().setHeader(Constants.DEFAULT_RABBIT_TOKEN_HEADER,
						MDC.get(Constants.DEFAULT_MDC_UUID_TOKEN_KEY));
				return m;
			});
	}
	@Bean
	Queue calculationQueue() {
		return new Queue(Constants.QUEUE_NAME, false);
	}

	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addContextValves(new LogbackValve());
		return tomcat;
	}

	
	

}