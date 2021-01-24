package com.mciefova.dbconnector.module.api.connector.connection;

import com.mciefova.dbconnector.exception.ApiException;
import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import com.mciefova.dbconnector.security.SecurityService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Abstract class defining connection creation.
 *
 */
public abstract class AbstractConnectionProvider {

    /**
     * Security service.
     */
    protected SecurityService security;

    public AbstractConnectionProvider(SecurityService security) {
        this.security = security;
    }

    /**
     * Create connection with the given connection configuration.
     *
     * @param connectionData connection configuration
     * @return connection
     */
    public Connection createConnection(ConnectionEntity connectionData) {

        try {
            Class.forName(getDriverClass());
            String userName = connectionData.getUserName();
            String password
                    = security.decryptPassword(connectionData.getPassword());

            return DriverManager.getConnection(
                    buildDatabaseUrl(connectionData), userName, password);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ApiException(ex);
        }
    }

    /**
     * Driver class.
     *
     * @return driver class
     */
    protected abstract String getDriverClass();

    /**
     * Build database URL based on the given connection configuration.
     *
     * @param connectionData connection configuration.
     * @return connection URL
     */
    protected abstract String buildDatabaseUrl(ConnectionEntity connectionData);
}
