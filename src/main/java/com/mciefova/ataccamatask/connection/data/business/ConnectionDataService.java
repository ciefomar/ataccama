package com.mciefova.ataccamatask.connection.data.business;

import com.mciefova.ataccamatask.connection.data.model.dto.ConnectionEntityDTO;
import com.mciefova.ataccamatask.security.SecurityService;
import com.mciefova.ataccamatask.connection.data.business.enums.InfoSize;
import com.mciefova.ataccamatask.exception.NotFoundException;
import com.mciefova.ataccamatask.connection.data.model.dao.ConnectionEntityDAO;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConnectionDataService {

    @Autowired
    private ConnectionEntityDAO connectionEntityDAO;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ConnectionDTOService connectionDTOService;

    public List<ConnectionEntityDTO> listAllConnections(InfoSize infoSize) {

        if (infoSize == InfoSize.SHORT) {
            List<ConnectionEntity> loadedConnections = connectionEntityDAO.listAllConnectionsShortInfo();
            return connectionDTOService.connectionEntityToDtoObject(loadedConnections);
        }

        return listAllConnections();
    }


    public List<ConnectionEntityDTO> listAllConnections() {
        List<ConnectionEntity> connectionList = connectionEntityDAO.listAllConnections();
        maskPasswords(connectionList);

        return connectionDTOService.connectionEntityToDtoObject(connectionList);
    }

    public ConnectionEntityDTO findConnection(String name) {

        return connectionDTOService.connectionEntityToDtoObject(findConnectionEntity(name));
    }

    public ConnectionEntity findConnectionEntity(String name) {
        ConnectionEntity connection = connectionEntityDAO.findConnection(name);

        if (connection == null) {
            throw new NotFoundException();
        }

        return connection;
    }

    public void createConnection(ConnectionEntityDTO connection) {
        connection.setPassword(securityService.encryptPassword(connection.getPassword()));
        connectionEntityDAO.create(connectionDTOService.connectionDtoToEntityObject(connection));
    }

    public void deleteConnection(String name) {
        connectionEntityDAO.deleteConnection(name);
    }

    public void deleteConnection(ConnectionEntityDTO connection) {
        connectionEntityDAO.delete(connectionDTOService.connectionDtoToEntityObject(connection));
    }

    public void updateConnection(ConnectionEntityDTO connection) {
        connectionEntityDAO.update(connectionDTOService.connectionDtoToEntityObject(connection));
    }

    public void updateConnection(Map<String, String> newValuesMap, String name) {
        connectionEntityDAO.updateConnection(newValuesMap, name);
    }

    private void maskPasswords(List<ConnectionEntity> connectionList) {
        connectionList.forEach(connection -> connection.setPassword(securityService.maskPassword()));
    }
}
