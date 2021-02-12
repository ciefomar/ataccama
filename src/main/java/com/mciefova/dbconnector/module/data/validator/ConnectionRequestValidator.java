package com.mciefova.dbconnector.module.data.validator;

import com.mciefova.dbconnector.exception.BadRequestException;
import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityRequest;
import com.mciefova.dbconnector.module.data.service.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *Connection request validator
 */
@Component
public class ConnectionRequestValidator {
    private final ConnectionRepository repository;

    @Autowired
    public ConnectionRequestValidator(ConnectionRepository repository) {
        this.repository = repository;
    }

    public boolean validate(ConnectionEntityRequest request) {
        if(repository.existsByName(request.getName())){
            throw new BadRequestException("Name has to be unique.");
        }

        return true;
    }

    public boolean validate(Long id, ConnectionEntityRequest request) {
        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new BadRequestException("Name has to be unique.");
        }

        return true;
    }
}
