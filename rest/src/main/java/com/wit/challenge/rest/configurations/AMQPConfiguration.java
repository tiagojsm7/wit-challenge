package com.wit.challenge.rest.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wit.challenge.entities.Constants;

import ch.qos.logback.access.tomcat.LogbackValve;

@Configuration
public class AMQPConfiguration {

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