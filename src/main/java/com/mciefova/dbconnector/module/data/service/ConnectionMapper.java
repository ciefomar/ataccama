package com.mciefova.dbconnector.module.data.service;

import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityRequest;
import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityResponse;
import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import com.mciefova.dbconnector.security.SecurityService;
import com.mciefova.dbconnector.service.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionMapper extends AbstractMapper<ConnectionEntity, ConnectionEntityRequest, ConnectionEntityResponse> {

    private SecurityService security;

    @Autowired
    public ConnectionMapper(ModelMapper modelMapper,
                            SecurityService security) {
        super(modelMapper, ConnectionEntity.class, ConnectionEntityResponse.class);

        modelMapper.createTypeMap(ConnectionEntity.class,
                                  ConnectionEntityResponse.class)
                .addMappings(mapper -> mapper.using(ctx -> security.maskPassword())
                .map(ConnectionEntity::getPassword,
                     ConnectionEntityResponse::setPassword));

        modelMapper.createTypeMap(ConnectionEntity.class, ConnectionEntity.class)
                .addMappings(map -> map.skip(ConnectionEntity::setId));
    }

}
