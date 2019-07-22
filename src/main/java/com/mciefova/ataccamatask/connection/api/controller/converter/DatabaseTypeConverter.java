package com.mciefova.ataccamatask.connection.api.controller.converter;

import com.mciefova.ataccamatask.connection.api.connection.enums.DatabaseType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTypeConverter implements Converter<String, DatabaseType> {
    @Override
    public DatabaseType convert(String databaseType) {
        return DatabaseType.valueOf(databaseType.toUpperCase());
    }
}