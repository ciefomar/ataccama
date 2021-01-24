package com.mciefova.dbconnector.module.data.service;

import com.mciefova.dbconnector.exception.NotFoundException;
import com.mciefova.dbconnector.module.data.InfoSize;
import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityRequest;
import com.mciefova.dbconnector.module.data.model.dto.ConnectionEntityResponse;
import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import com.mciefova.dbconnector.security.SecurityService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionDataService {

    private final ConnectionRepository repository;

    private final SecurityService securityService;

    private final ConnectionMapper mapper;

    @Autowired
    public ConnectionDataService(ConnectionRepository repository,
                                 SecurityService securityService,
                                 ConnectionMapper mapper) {
        this.repository = repository;
        this.securityService = securityService;
        this.mapper = mapper;
    }

    public List<ConnectionEntityResponse>
            listAllConnections(Optional<InfoSize> size) {
        Iterable<ConnectionEntity> connectionList = repository.findAll();

        return mapper.mapToResponse(connectionList);
    }

    public ConnectionEntityResponse findConnection(Long id) {
        return mapper.mapToResponse(loadFromDB(id));
    }

    public ConnectionEntityResponse
            createConnection(ConnectionEntityRequest connectionDto) {
        ConnectionEntity connection = mapper.map(connectionDto);
        connection.setPassword(
                securityService.encryptPassword(connection.getPassword()));
        repository.save(connection);

        return mapper.mapToResponse(connection);
    }

    public ConnectionEntityResponse
            updateConnection(Long id, ConnectionEntityRequest updateDto) {
        ConnectionEntity original = repository.findById(id)
                .orElseThrow(NotFoundException::new);
        ConnectionEntity updated
                = mapper.update(original, updateDto);
        updated.setPassword(
                securityService.encryptPassword(updated.getPassword()));
        repository.save(updated);
        return mapper.mapToResponse(updated);
    }

    public void deleteConnection(Long id) {
        repository.deleteById(id);
    }

    public ConnectionEntity loadFromDB(Long id) {
        ConnectionEntity connection = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        return connection;
    }
}
