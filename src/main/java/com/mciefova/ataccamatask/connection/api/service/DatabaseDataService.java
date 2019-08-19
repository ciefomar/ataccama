package com.mciefova.ataccamatask.connection.api.service;

import com.mciefova.ataccamatask.connection.api.connection.factory.ConnectionWrapper;
import com.mciefova.ataccamatask.connection.api.dto.SchemaDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableColumnDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableDTO;
import com.mciefova.ataccamatask.connection.api.queries.DatabaseQueriesProvider;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;


@Service
public class DatabaseDataService {

    public List<SchemaDTO> listDatabaseSchemas(ConnectionWrapper connectionWrapper) throws SQLException {

        List<SchemaDTO> loadedSchemas;
        try (Statement statement = connectionWrapper.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(
                    connectionWrapper.getDatabaseQueriesProvider().createListAllSchemasQuery());
            loadedSchemas = connectionWrapper.getSchemaInfoReader().readInfo(result);
        }

        return loadedSchemas;
    }

    public List<TableDTO> listDatabaseTables(ConnectionWrapper connectionWrapper,
                                             String schema) throws SQLException {

        List<TableDTO> loadedTables;
        if (schema.isEmpty()) {
            try (Statement statement = connectionWrapper.getConnection().createStatement()) {
                ResultSet result = statement.executeQuery(
                        connectionWrapper.getDatabaseQueriesProvider().createListAllTablesQuery()
                );
                loadedTables = connectionWrapper.getTableInfoReader().readInfo(result);
            }
        } else {
            try (PreparedStatement listTablesFromSchema =
                         connectionWrapper.getConnection().prepareStatement(
                                 connectionWrapper.getDatabaseQueriesProvider().createListAllTablesFromSchemaQuery())) {
                listTablesFromSchema.setString(1, schema);
                ResultSet result = listTablesFromSchema.executeQuery();
                loadedTables = connectionWrapper.getTableInfoReader().readInfo(result);
            }
        }

        return loadedTables;
    }

    public List<TableColumnDTO> listDatabaseTableColumns(ConnectionWrapper connectionWrapper,
                                                         String schema,
                                                         String table) throws SQLException {

        List<TableColumnDTO> loadedTableColumns;
        try (PreparedStatement listTablesFromSchema =
                     connectionWrapper.getConnection().prepareStatement(
                             connectionWrapper.getDatabaseQueriesProvider().createListAllColumnsFromTableQuery())) {
            listTablesFromSchema.setString(1, schema);
            listTablesFromSchema.setString(2, table);

            ResultSet result = listTablesFromSchema.executeQuery();
            loadedTableColumns = connectionWrapper.getTableColumnInfoReader().readInfo(result);
        }

        return loadedTableColumns;
    }

    public List<Map<String, String>> loadTableView(ConnectionWrapper connectionWrapper,
                                                   String schema, String table, String columns, String orderBy,
                                                   String limit) throws SQLException {

        List<Map<String, String>> recordList = new ArrayList<>();

        String sqlQuery = createTableViewQuery(schema, table, columns, orderBy, limit,
                                               connectionWrapper.getDatabaseQueriesProvider()
        );

        try (Statement statement = connectionWrapper.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(sqlQuery);

            LinkedList<String> columnNames = readResultSetColumnNames(result);

            while (result.next()) {
                LinkedHashMap<String, String> record = new LinkedHashMap<>();

                for (String columnName : columnNames) {
                    record.put(columnName, result.getString(columnName));
                }

                recordList.add(record);
            }
        }

        return recordList;
    }

    private String createTableViewQuery(String schema, String table, String columns, String orderBy, String limit,
                                        DatabaseQueriesProvider queriesProvider) {
        if (columns != null && !columns.isEmpty()
                || orderBy != null && !orderBy.isEmpty()
                || limit != null && !limit.isEmpty()) {

            return queriesProvider.createSelectQuery(schema, table, columns, orderBy, limit);
        } else {
            return queriesProvider.createLoadTableViewQuery(schema, table);
        }
    }

    private LinkedList<String> readResultSetColumnNames(ResultSet result) throws SQLException {
        LinkedList<String> columnNames = new LinkedList<>();

        ResultSetMetaData resultMetaData = result.getMetaData();

        for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
            columnNames.add(resultMetaData.getColumnName(i));
        }

        return columnNames;
    }
}
