package com.mciefova.dbconnector.module.api.service;

import com.mciefova.dbconnector.DatabaseType;
import com.mciefova.dbconnector.exception.ApiException;
import com.mciefova.dbconnector.module.api.connector.Connector;
import com.mciefova.dbconnector.module.api.connector.ConnectorLoader;
import com.mciefova.dbconnector.module.api.dto.*;
import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import com.mciefova.dbconnector.module.data.service.ConnectionDataService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service creating connections and providing database operation.
 */
@Service
public class ConnectionService {

    /**
     * Loads requested connector.
     */
    private final ConnectorLoader connectorLoader;

    /**
     * Loads connection configuration.
     *
     * @see ConnectionEntity
     */
    private final ConnectionDataService connectionDataService;

    /**
     * Provides database operations.
     */
    private final DatabaseDataService databaseDataService;

    @Autowired
    public ConnectionService(ConnectorLoader connectorLoader,
                             ConnectionDataService connectionDataService,
                             DatabaseDataService databaseDataService) {
        this.connectorLoader = connectorLoader;
        this.connectionDataService = connectionDataService;
        this.databaseDataService = databaseDataService;
    }

    /**
     * List database schema.
     *
     * @param connectionId connection entity id
     * @return schema list
     */
    public Iterable<SchemaDTO> listDatabaseSchemas(Long connectionId) {

        ConnectionEntity connectionData
                = connectionDataService.loadFromDB(connectionId);
        DatabaseType databaseType = connectionData.getDatabaseType();

        try {
            Connector connector = connectorLoader.loadConnector(databaseType);
            return databaseDataService
                    .listDatabaseSchemas(connector, connectionData);
        } catch (Exception ex) {
            throw new ApiException(ex);
        }
    }

    /**
     * List database tables.
     *
     * @param connectionId connection entity id
     * @param schema schema name
     * @return table list
     */
    public Iterable<TableDTO> listDatabaseTables(Long connectionId,
                                                 Optional<String> schema) {

        ConnectionEntity connectionData
                = connectionDataService.loadFromDB(connectionId);
        DatabaseType databaseType = connectionData.getDatabaseType();

        try {
            Connector connector = connectorLoader.loadConnector(databaseType);
            return databaseDataService
                    .listDatabaseTables(connector, connectionData, schema);
        } catch (Exception ex) {
            throw new ApiException(ex);
        }
    }

    /**
     * List table columns.
     *
     * @param connectionId connection entity id
     * @param schema schema name
     * @param table table name
     * @return column list
     */
    public Iterable<TableColumnDTO> listTableColumns(Long connectionId,
                                                     Optional<String> schema,
                                                     Optional<String> table) {
        ConnectionEntity connectionData
                = connectionDataService.loadFromDB(connectionId);
        DatabaseType databaseType = connectionData.getDatabaseType();

        try {
            Connector connector = connectorLoader.loadConnector(databaseType);
            return databaseDataService
                    .listDatabaseTableColumns(connector, connectionData,
                                              schema, table);
        } catch (Exception ex) {
            throw new ApiException(ex);
        }
    }

    /**
     * Load table view.
     * @param connectionId connection entity id
     * @param schema schema name
     * @param table table name
     * @param orderBy column to order by
     * @param limit record number limit
     * @return table view
     */
    public Iterable<Record> loadTableView(Long connectionId, String schema,
                                          String table, Optional<String> orderBy,
                                          Optional<String> limit) {

        ConnectionEntity connectionData
                = connectionDataService.loadFromDB(connectionId);
        DatabaseType databaseType = connectionData.getDatabaseType();

        try {
            Connector connector = connectorLoader.loadConnector(databaseType);
            return databaseDataService
                    .loadTableView(connector, connectionData, schema,
                                   table, orderBy, limit);
        } catch (Exception ex) {
            throw new ApiException(ex);
        }

    }
}
