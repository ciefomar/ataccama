package com.mciefova.ataccamatask.connection.api.controller.converter;

import com.mciefova.ataccamatask.connection.api.enums.DataType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataTypeConverter implements Converter<String, DataType> {

    private static final List<String> STRING_DB_TYPES = Arrays.asList("character", "text");
    private static final List<String> NUMERIC_DB_TYPES = Arrays.asList("integer", "real", "numeric");
    private static final List<String> DATE_DB_TYPES = Arrays.asList("date", "time", "timestamp", "interval");

    @Override
    public DataType convert(String dataType) {

        if (stringDataType(dataType)) {
            return DataType.STRING;
        } else if (numericDataType(dataType)) {
            return DataType.NUMBER;
        } else if (dateDataType(dataType)) {
            return DataType.DATE;
        }

        return DataType.GENERAL;
    }

    private boolean stringDataType (String dataType) {
        return STRING_DB_TYPES
                .stream()
                .anyMatch(dataType::contains);
    }

    private boolean numericDataType (String dataType) {
        return NUMERIC_DB_TYPES
                .stream()
                .anyMatch(dataType::contains);
    }

    private boolean dateDataType (String dataType) {
        return DATE_DB_TYPES
                .stream()
                .anyMatch(dataType::contains);
    }
}
