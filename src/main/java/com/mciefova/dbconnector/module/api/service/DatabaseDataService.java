package com.mciefova.dbconnector.module.api.service;

import com.mciefova.dbconnector.module.api.connector.Connector;
import com.mciefova.dbconnector.module.api.dto.Record;
import com.mciefova.dbconnector.module.api.dto.SchemaDTO;
import com.mciefova.dbconnector.module.api.dto.TableColumnDTO;
import com.mciefova.dbconnector.module.api.dto.TableDTO;
import com.mciefova.dbconnector.module.api.service.reader.ColumnReader;
import com.mciefova.dbconnector.module.api.service.reader.RecordReader;
import com.mciefova.dbconnector.module.api.service.reader.SchemaReader;
import com.mciefova.dbconnector.module.api.service.reader.TableReader;
import com.mciefova.dbconnector.module.data.model.entities.ConnectionEntity;
import java.sql.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Database operations.
 * 
 */
@Service
public class DatabaseDataService {

    private final SchemaReader schemaReader;

    private final TableReader tableReader;

    private final ColumnReader columnReader;

    private final RecordReader recordReader;

    @Autowired
    public DatabaseDataService(SchemaReader schemaReader,
                               TableReader tableReader,
                               ColumnReader columnReader,
                               RecordReader recordReader) {
        this.schemaReader = schemaReader;
        this.tableReader = tableReader;
        this.columnReader = columnReader;
        this.recordReader = recordReader;
    }

    public Iterable<SchemaDTO>
            listDatabaseSchemas(Connector connector,
                                ConnectionEntity connectionData) throws SQLException {

        try (Connection connection = connector.createConnection(connectionData)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet schemas = databaseMetaData.getSchemas();
            return schemaReader.readInfo(schemas);
        }
    }

    public Iterable<TableDTO>
            listDatabaseTables(Connector connector,
                               ConnectionEntity connectionData,
                               Optional<String> schema) throws SQLException {

        try (Connection connection = connector.createConnection(connectionData)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet tables;

            if (schema.isPresent()) {
                tables = databaseMetaData.getTables(null, schema.get(), null,
                                                    new String[]{"TABLE"});
            } else {
                tables = databaseMetaData.getTables(null, null, null,
                                                    new String[]{"TABLE"});
            }
            return tableReader.readInfo(tables);

        }
    }

    public Iterable<TableColumnDTO>
            listDatabaseTableColumns(Connector connector,
                                     ConnectionEntity connectionData,
                                     Optional<String> schema,
                                     Optional<String> table) throws SQLException {

        try (Connection connection = connector.createConnection(connectionData)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet columns = databaseMetaData.getColumns(
                    null, schema.isPresent() ? schema.get() : null,
                    table.isPresent() ? table.get() : null, null);
            return columnReader.readInfo(columns);
        }
    }

    public Iterable<Record>
            loadTableView(Connector connector,
                          ConnectionEntity connectionData,
                          String schema, String table, Optional<String> orderBy,
                          Optional<String> limit) throws SQLException {

        String sqlQuery
                = connector.getQueriesProvider()
                        .createLoadTableViewQuery(schema, table, limit, orderBy);

        try (Connection connection = connector.createConnection(connectionData);
                Statement statement = connection.createStatement();) {
            ResultSet result = statement.executeQuery(sqlQuery);

            return recordReader.readInfo(result);
        }
    }

}
