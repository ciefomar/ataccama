package com.mciefova.ataccamatask.connection.api.connection.factory;

import com.mciefova.ataccamatask.connection.api.connection.creator.PostrgesqlConnectionCreator;
import com.mciefova.ataccamatask.connection.api.connection.enums.DatabaseType;
import com.mciefova.ataccamatask.connection.api.controller.converter.DatabaseTypeConverter;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class ConnectionFactory {

    @Autowired
    private PostrgesqlConnectionCreator postrgesqlConnectionCreator;

    public Connection createConnection (ConnectionEntity connection, DatabaseType databaseType) {

        switch (databaseType) {
            case POSTGRESQL:
                return postrgesqlConnectionCreator.createConnection(connection);
            case MYSQL:
                return null;
            case ORACLE:
                return null;
            default:
                return null;
        }
    }
}
