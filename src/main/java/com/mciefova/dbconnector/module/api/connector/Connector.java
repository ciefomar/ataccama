package com.mciefova.dbconnector.module.api.connector;

import com.mciefova.dbconnector.DatabaseType;
import com.mciefova.dbconnector.module.api.connector.queries.DatabaseQueriesProvider;
import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import java.sql.Connection;

/**
 * Connector interface.
 */
public interface Connector {

    /**
     * Create connection.
     *
     * @param connectionData connection configuration
     * @return connection
     */
    Connection createConnection(ConnectionEntity connectionData);

    /**
     * Load queries provider.
     *
     * @return queries provider
     */
    DatabaseQueriesProvider getQueriesProvider();

    /**
     * Supported database type.
     *
     * @return supported database type
     */
    DatabaseType supportedDatabaseType();
}
