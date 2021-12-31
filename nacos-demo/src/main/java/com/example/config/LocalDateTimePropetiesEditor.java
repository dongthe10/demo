package com.example.config;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author hollly
 * @date 2020/12/19 23:50
 */
@Component
public class LocalDateTimePropetiesEditor extends PropertyEditorSupport {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String getAsText() {
        Object value = this.getValue();
        //dateTimeFormatter
        return Objects.nonNull(value) ? "not valid" : "not";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LocalDate parse = LocalDate.parse(text, dateTimeFormatter);
        this.setValue(parse);
    }
}
