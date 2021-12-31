package com.example.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author hollly
 * @date 2020/12/20 0:17
 */
@Component
public class LocalDateTimeConverter implements Converter<String, LocalDate> {


    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String source) {
        return LocalDate.parse(source, dateTimeFormatter);

    }
}
