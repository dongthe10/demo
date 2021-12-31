package com.example.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.time.temporal.Temporal;

/**
 * @author hollly
 * @date 2020/12/20 0:21
 */
public class LocalDateConverterFactory implements ConverterFactory<String, Temporal> {
    @Override
    public <T extends Temporal> Converter<String, T> getConverter(Class<T> targetType) {
        return null;
    }
}
