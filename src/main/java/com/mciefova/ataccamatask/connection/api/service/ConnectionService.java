package com.mciefova.ataccamatask.connection.api.service;

import com.mciefova.ataccamatask.connection.api.connection.enums.DatabaseType;
import com.mciefova.ataccamatask.connection.api.connection.factory.ConnectionFactory;
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
    private ConnectionFactory connectionFactory;

    @Autowired
    private ConnectionDataService connectionDataService;

    @Autowired
    private DatabaseTypeConverter databaseTypeConverter;

    @Autowired
    private DatabaseDataService databaseDataService;


    public List<SchemaDTO> listDatabaseSchemas(String connectionName) {

        ConnectionEntity connectionData = connectionDataService.findConnection(connectionName);
        DatabaseType databaseType = databaseTypeConverter.convert(connectionData.getDatabaseType());

        try (Connection connection = connectionFactory.createConnection(connectionData, databaseType)) {

            return databaseDataService.listDatabaseSchemas(connection, databaseType);

        } catch (SQLException ex) {
            throw new ApiException(ex);
        }
    }

    public List<TableDTO> listDatabaseTables(String connectionName) {
        return listDatabaseTables(connectionName, "");
    }

    public List<TableDTO> listDatabaseTables(String connectionName, String schema) {

        ConnectionEntity connectionData = connectionDataService.findConnection(connectionName);
        DatabaseType databaseType = databaseTypeConverter.convert(connectionData.getDatabaseType());

        try (Connection connection = connectionFactory.createConnection(connectionData, databaseType)) {

            return databaseDataService.listDatabaseTables(connection, databaseType, schema);

        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

    }

    public List<TableColumnDTO> listTableColumns(String connectionName, String schema, String table) {
        ConnectionEntity connectionData = connectionDataService.findConnection(connectionName);
        DatabaseType databaseType = databaseTypeConverter.convert(connectionData.getDatabaseType());

        try (Connection connection = connectionFactory.createConnection(connectionData, databaseType)) {

            return databaseDataService.listDatabaseTableColumns(connection, databaseType, schema, table);

        } catch (SQLException ex) {
            throw new ApiException(ex);
        }
    }

    public List<Map<String, String>> loadTableView(String connectionName, String schema, String table, String columns,
                                                   String orderBy, String limit) {

        ConnectionEntity connectionData = connectionDataService.findConnection(connectionName);
        DatabaseType databaseType = databaseTypeConverter.convert(connectionData.getDatabaseType());

        try (Connection connection = connectionFactory.createConnection(connectionData, databaseType)) {

            return databaseDataService.loadTableView(connection, databaseType, schema, table, columns, orderBy, limit);

        } catch (SQLException ex) {
            throw new ApiException(ex);
        }

    }
}
