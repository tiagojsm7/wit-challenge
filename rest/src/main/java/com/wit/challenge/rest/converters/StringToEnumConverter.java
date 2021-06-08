package com.wit.challenge.rest.converters;

import org.springframework.core.convert.converter.Converter;

import com.wit.challenge.entities.Operations;

public class StringToEnumConverter implements Converter<String, Operations> {
    @Override
    public Operations convert(String source) {
            return Operations.valueOf(source.toUpperCase());
    }
}