package com.mciefova.ataccamatask.connection.data.controller.converter;

import com.mciefova.ataccamatask.connection.data.business.enums.InfoSize;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InfoSizeConverter implements Converter<String, InfoSize> {
    @Override
    public InfoSize convert(String infoSize) {
        return InfoSize.valueOf(infoSize.toUpperCase());
    }
}