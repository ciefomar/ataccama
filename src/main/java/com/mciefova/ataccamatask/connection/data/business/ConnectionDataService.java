package com.mciefova.ataccamatask.connection.data.business;

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

    public List<ConnectionEntity> listAllConnections(InfoSize infoSize) {

        if (infoSize == InfoSize.SHORT) {
            return connectionEntityDAO.listAllConnectionsShortInfo();
        }

        return listAllConnections();
    }


    public List<ConnectionEntity> listAllConnections() {
        List<ConnectionEntity> connectionList = connectionEntityDAO.listAllConnections();
        maskPasswords(connectionList);

        return connectionList;
    }

    public ConnectionEntity findConnection(String name) {
        ConnectionEntity connection = connectionEntityDAO.findConnection(name);

        if (connection == null) {
            throw new NotFoundException();
        }

        return connection;
    }

    public void createConnection(ConnectionEntity connection) {
        connection.setPassword(securityService.encryptPassword(connection.getPassword()));
        connectionEntityDAO.create(connection);
    }

    public void deleteConnection(String name) {
        connectionEntityDAO.deleteConnection(name);
    }

    public void deleteConnection(ConnectionEntity connection) {
        connectionEntityDAO.delete(connection);
    }

    public void updateConnection(ConnectionEntity connection) {
        connectionEntityDAO.update(connection);
    }

    public void updateConnection(Map<String, String> newValuesMap, String name) {
        connectionEntityDAO.updateConnection(newValuesMap, name);
    }

    private void maskPasswords(List<ConnectionEntity> connectionList) {
        connectionList.forEach(connection -> connection.setPassword(securityService.maskPassword()));
    }
}
