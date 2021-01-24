package com.mciefova.dbconnector.module.api.connector;

import com.mciefova.dbconnector.DatabaseType;
import com.mciefova.dbconnector.module.api.connector.connection.PostgresqlConnectionProvider;
import com.mciefova.dbconnector.module.api.connector.queries.DatabaseQueriesProvider;
import com.mciefova.dbconnector.module.api.connector.queries.PostgresqlQueriesProvider;
import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostgresqlConnector implements Connector {

    /**
     * Connection provider.
     */
    private final PostgresqlConnectionProvider connectionProvider;

    /**
     * Queries provider.
     */
    private final PostgresqlQueriesProvider queriesProvider;

    @Autowired
    public PostgresqlConnector(PostgresqlConnectionProvider connectionProvider,
                               PostgresqlQueriesProvider queriesProvider) {
        this.connectionProvider = connectionProvider;
        this.queriesProvider = queriesProvider;
    }

    /**
     * {@inheritDoc}.
     * @param connectionData connection configuration
     * @return connection
     */
    @Override
    public Connection createConnection(ConnectionEntity connectionData) {
        return connectionProvider.createConnection(connectionData);
    }

    /**
     * {@inheritDoc}.
     * @return queries provider
     */
    @Override
    public DatabaseQueriesProvider getQueriesProvider() {
        return this.queriesProvider;
    }

    /**
     * {@inheritDoc}.
     * @return database type
     */
    @Override
    public DatabaseType supportedDatabaseType() {
        return DatabaseType.POSTGRESQL;
    }

}
