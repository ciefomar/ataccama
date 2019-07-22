package com.mciefova.ataccamatask.connection.api.connection.url.creator;

import com.mciefova.ataccamatask.security.SecurityService;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostgresqlDatabaseUrlCreator implements DatabaseUrlCreator {

    private static final String CONNECTION_URL_TEMPLATE =  "jdbc:postgresql://%s:%s/%s?user=%s&password=%s";

    @Autowired
    private SecurityService securityService;


    @Override
    public String buildDatabaseUrl(ConnectionEntity connectionData) {
        return String.format(CONNECTION_URL_TEMPLATE,
                             connectionData.getHost(),
                             connectionData.getPort(),
                             connectionData.getDatabaseName(),
                             connectionData.getUserName(),
                             securityService.decryptPassword(connectionData.getPassword()));
    }
}
