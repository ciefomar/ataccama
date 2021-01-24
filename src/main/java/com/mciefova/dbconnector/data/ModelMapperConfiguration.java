package com.mciefova.dbconnector.data;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration defining model mapper bean.
 */
@Configuration
public class ModelMapperConfiguration {

    /**
     * Model mapper bean.
     * @return model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
