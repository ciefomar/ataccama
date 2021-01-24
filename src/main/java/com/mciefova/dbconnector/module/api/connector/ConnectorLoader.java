package com.mciefova.dbconnector.module.api.connector;

import com.mciefova.dbconnector.DatabaseType;
import com.mciefova.dbconnector.exception.DatabaseNotSupportedException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Component loading connector by requested database type.
 *
 */
@Component
public class ConnectorLoader {

    /**
     * Available connectors.
     */
    Set<Connector> connectors;

    @Autowired
    public ConnectorLoader(Set<Connector> connectors) {
        this.connectors = connectors;
    }

    /**
     * Load connector supporting requested database type.
     *
     * @param databaseType database type
     * @return connector
     */
    public Connector loadConnector (DatabaseType databaseType){
        return connectors.stream()
                .filter(con -> databaseType == con.supportedDatabaseType())
                .findFirst()
                .orElseThrow(DatabaseNotSupportedException::new);
    }

}
