package com.mciefova.ataccamatask.connection.api.service;

import com.mciefova.ataccamatask.connection.api.dto.SchemaDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableColumnDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableDTO;
import com.mciefova.ataccamatask.connection.api.queries.DatabaseQueriesProvider;
import com.mciefova.ataccamatask.connection.api.service.reader.DbInfoReader;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;


@Service
public class DatabaseDataService {

    public List<SchemaDTO> listDatabaseSchemas(Connection connection,
                                               DatabaseQueriesProvider databaseQueriesProvider,
                                               DbInfoReader<SchemaDTO> schemaInfoReader) throws SQLException {

        List<SchemaDTO> loadedSchemas;
        try(Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(databaseQueriesProvider.createListAllSchemasQuery());
            loadedSchemas = schemaInfoReader.readInfo(result);
        }

        return loadedSchemas;
    }

    public List<TableDTO> listDatabaseTables(Connection connection,
                                             DatabaseQueriesProvider databaseQueriesProvider,
                                             DbInfoReader<TableDTO> tableInfoReader,
                                             String schema) throws SQLException {

        List<TableDTO> loadedTables;
        if (schema.isEmpty()) {
            try(Statement statement = connection.createStatement()) {
                ResultSet result = statement.executeQuery(databaseQueriesProvider.createListAllTablesQuery());
                loadedTables = tableInfoReader.readInfo(result);
            }
        } else {
            try (PreparedStatement listTablesFromSchema =
                    connection.prepareStatement(databaseQueriesProvider.createListAllTablesFromSchemaQuery())) {
                listTablesFromSchema.setString(1, schema);
                ResultSet result = listTablesFromSchema.executeQuery();
                loadedTables = tableInfoReader.readInfo(result);
            }
        }

        return loadedTables;
    }

    public List<TableColumnDTO> listDatabaseTableColumns(Connection connection,
                                                         DatabaseQueriesProvider databaseQueriesProvider,
                                                         DbInfoReader<TableColumnDTO> tableColumnInfoReader,
                                                         String schema,
                                                         String table) throws SQLException {

        List<TableColumnDTO> loadedTableColumns;
        try(PreparedStatement listTablesFromSchema =
                connection.prepareStatement(databaseQueriesProvider.createListAllColumnsFromTableQuery())) {
            listTablesFromSchema.setString(1, schema);
            listTablesFromSchema.setString(2, table);

            ResultSet result = listTablesFromSchema.executeQuery();
            loadedTableColumns = tableColumnInfoReader.readInfo(result);
        }

        return loadedTableColumns;
    }

    public List<Map<String, String>> loadTableView(Connection connection, DatabaseQueriesProvider databaseQueriesProvider,
                                                   String schema, String table, String columns, String orderBy,
                                                   String limit) throws SQLException {

        List<Map<String, String>> recordList = new ArrayList<>();

        String sqlQuery = createTableViewQuery(schema, table, columns, orderBy, limit,
                                               databaseQueriesProvider);

        try(Statement statement = connection.createStatement()) {
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
