package com.mciefova.ataccamatask.connection.api.connection.creator;

import com.mciefova.ataccamatask.connection.api.connection.factory.ConnectionWrapper;
import com.mciefova.ataccamatask.connection.api.connection.url.creator.PostgresqlDatabaseUrlCreator;
import com.mciefova.ataccamatask.connection.api.queries.PostgresqlQueriesProvider;
import com.mciefova.ataccamatask.connection.api.service.reader.postgresql.PostgresqlSchemaInfoReader;
import com.mciefova.ataccamatask.connection.api.service.reader.postgresql.PostgresqlTableColumnInfoReader;
import com.mciefova.ataccamatask.connection.api.service.reader.postgresql.PostgresqlTableInfoReader;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import com.mciefova.ataccamatask.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class PostrgesqlConnectionWrapperCreator implements ConnectionWrapperCreator {

    private static final String DRIVER_CLASS = "org.postgresql.Driver";

    @Autowired
    private PostgresqlDatabaseUrlCreator urlCreator;

    @Autowired
    private PostgresqlSchemaInfoReader schemaInfoReader;

    @Autowired
    private PostgresqlTableInfoReader tableInfoReader;

    @Autowired
    private PostgresqlTableColumnInfoReader tableColumnInfoReader;

    @Autowired
    private PostgresqlQueriesProvider databaseQueriesProvider;

    @Override
    public ConnectionWrapper createConnectioWrapper(ConnectionEntity connectionData) {
        return new ConnectionWrapper(
                        createConnection(connectionData),
                        schemaInfoReader,
                        tableInfoReader,
                        tableColumnInfoReader,
                        databaseQueriesProvider
                );
    }

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
