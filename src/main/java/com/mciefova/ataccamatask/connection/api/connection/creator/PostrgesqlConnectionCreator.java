package com.mciefova.ataccamatask.connection.api.connection.creator;

import com.mciefova.ataccamatask.connection.api.connection.url.creator.PostgresqlDatabaseUrlCreator;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import com.mciefova.ataccamatask.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class PostrgesqlConnectionCreator implements ConnectionCreator{

    private static final String DRIVER_CLASS = "org.postgresql.Driver";

    @Autowired
    private PostgresqlDatabaseUrlCreator urlCreator;

    @Override
    public Connection createConnection(ConnectionEntity connectionData) {

        try {
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(urlCreator.buildDatabaseUrl(connectionData));

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ApiException(ex);
        }
    }
}
