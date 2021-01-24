package com.mciefova.dbconnector.module.data.service;

import com.mciefova.dbconnector.module.data.InfoSize;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InfoSizeConverter implements Converter<String, InfoSize> {
    @Override
    public InfoSize convert(String infoSize) {
        return InfoSize.valueOf(infoSize.toUpperCase());
    }
}
