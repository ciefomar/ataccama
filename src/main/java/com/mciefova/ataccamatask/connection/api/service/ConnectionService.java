package com.mciefova.ataccamatask.connection.api.service;

import com.mciefova.ataccamatask.connection.api.connection.enums.DatabaseType;
import com.mciefova.ataccamatask.connection.api.connection.factory.ConnectionWrapper;
import com.mciefova.ataccamatask.connection.api.connection.factory.ConnectionWrapperFactory;
import com.mciefova.ataccamatask.connection.api.controller.converter.DatabaseTypeConverter;
import com.mciefova.ataccamatask.connection.api.dto.*;
import com.mciefova.ataccamatask.connection.data.business.ConnectionDataService;
import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import com.mciefova.ataccamatask.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionWrapperFactory connectionWrapperFactory;

    @Autowired
    private ConnectionDataService connectionDataService;

    @Autowired
    private DatabaseTypeConverter databaseTypeConverter;

    @Autowired
    private DatabaseDataService databaseDataService;


    public List<SchemaDTO> listDatabaseSchemas(String connectionName) {

        ConnectionEntity connectionData = connectionDataService.findConnectionEntity(connectionName);
        DatabaseType databaseType = databaseTypeConverter.convert(connectionData.getDatabaseType());

        try {
            ConnectionWrapper connectionWrapper = connectionWrapperFactory.createConnectionWrapper(connectionData, databaseType);
            return databaseDataService.listDatabaseSchemas(
                    connectionWrapper.getConnection(),
                    connectionWrapper.getDatabaseQueriesProvider(),
                    connectionWrapper.getSchemaInfoReader());

        } catch (SQLException ex) {
            throw new ApiException(ex);
        }
    }

    public List<TableDTO> listDatabaseTables(String connectionName) {
        return listDatabaseTables(connectionName, "");
    }

    public List<TableDTO> listDatabaseTables(String connectionName, String schema) {

        ConnectionEntity connectionData = connectionDataService.findConnectionEntity(connectionName);
        DatabaseType databaseType = databaseTypeConverter.convert(connectionData.getDatabaseType());

        try {
            ConnectionWrapper connectionWrapper =
                    connectionWrapperFactory.createConnectionWrapper(connectionData, databaseType);

            return databaseDataService.listDatabaseTables(
                    connectionWrapper.getConnection(),
                    connectionWrapper.getDatabaseQueriesProvider(),
                    connectionWrapper.getTableInfoReader(),
                    schema);

        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

    }

    public List<TableColumnDTO> listTableColumns(String connectionName, String schema, String table) {
        ConnectionEntity connectionData = connectionDataService.findConnectionEntity(connectionName);
        DatabaseType databaseType = databaseTypeConverter.convert(connectionData.getDatabaseType());

        try {
            ConnectionWrapper connectionWrapper =
                    connectionWrapperFactory.createConnectionWrapper(connectionData, databaseType);

            return databaseDataService.listDatabaseTableColumns(
                    connectionWrapper.getConnection(),
                    connectionWrapper.getDatabaseQueriesProvider(),
                    connectionWrapper.getTableColumnInfoReader(),
                    schema,
                    table);

        } catch (SQLException ex) {
            throw new ApiException(ex);
        }
    }

    public List<Map<String, String>> loadTableView(String connectionName, String schema, String table, String columns,
                                                   String orderBy, String limit) {

        ConnectionEntity connectionData = connectionDataService.findConnectionEntity(connectionName);
        DatabaseType databaseType = databaseTypeConverter.convert(connectionData.getDatabaseType());

        try {

            ConnectionWrapper connectionWrapper =
                    connectionWrapperFactory.createConnectionWrapper(connectionData, databaseType);

            return databaseDataService.loadTableView(
                    connectionWrapper.getConnection(),
                    connectionWrapper.getDatabaseQueriesProvider(),
                    schema, table, columns, orderBy, limit);

        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

    }
}
