package com.mciefova.dbconnector.module.api.connector.connection;

import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import com.mciefova.dbconnector.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostgresqlConnectionProvider extends AbstractConnectionProvider
        implements ConnectionProvider {

    private static final String DRIVER_CLASS = "org.postgresql.Driver";
    private static final String CONNECTION_URL_TEMPLATE = "jdbc:postgresql://%s:%s/%s";

    @Autowired
    public PostgresqlConnectionProvider(SecurityService security) {
        super(security);
    }

    /**
     * {@inheritDoc }.
     * @return driver class
     */
    @Override
    protected String getDriverClass() {
        return DRIVER_CLASS;
    }

    /**
     * {@inheritDoc }.
     *
     * @return connection URL
     */
    @Override
    protected String buildDatabaseUrl(ConnectionEntity connectionData) {
        return String.format(CONNECTION_URL_TEMPLATE,
                             connectionData.getHost(),
                             connectionData.getPort(),
                             connectionData.getDatabaseName());
    }

}
