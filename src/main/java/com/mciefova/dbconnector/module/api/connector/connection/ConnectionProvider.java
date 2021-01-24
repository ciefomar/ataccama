package com.mciefova.dbconnector.module.api.connector.connection;

import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import java.sql.Connection;

/**
 * Connection provider.
 */
public interface ConnectionProvider {

    /**
     * Create connection.
     *
     * @param connectionData connection configuration
     * @return connection
     */
    Connection createConnection(ConnectionEntity connectionData);
}
