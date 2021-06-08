package com.wit.challenge.rest.configurations;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wit.challenge.rest.filters.Slf4jMDCFilter;

import com.wit.challenge.entities.Constants;

@Configuration
public class Slf4jMDCFilterConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> servletRegistrationBean() {
        final FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<Filter>();
        final Slf4jMDCFilter log4jMDCFilterFilter = new Slf4jMDCFilter(Constants.DEFAULT_RESPONSE_TOKEN_HEADER,
        		Constants.DEFAULT_MDC_UUID_TOKEN_KEY, null);
        registrationBean.setFilter(log4jMDCFilterFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}