package com.mciefova.ataccamatask.connection.api.service;

import com.mciefova.ataccamatask.connection.api.connection.enums.DatabaseType;
import com.mciefova.ataccamatask.connection.api.dto.SchemaDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableColumnDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableDTO;
import com.mciefova.ataccamatask.connection.api.queries.DatabaseQueriesProvider;
import com.mciefova.ataccamatask.connection.api.queries.PostgresqlQueriesProvider;
import com.mciefova.ataccamatask.connection.api.service.reader.DbInfoReader;
import com.mciefova.ataccamatask.connection.api.service.reader.postgresql.PostgresqlSchemaInfoReader;
import com.mciefova.ataccamatask.connection.api.service.reader.postgresql.PostgresqlTableColumnInfoReader;
import com.mciefova.ataccamatask.connection.api.service.reader.postgresql.PostgresqlTableInfoReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.sql.*;
import java.util.*;


@Service
public class DatabaseDataService {

    @Autowired
    private PostgresqlQueriesProvider postgresqlQueriesProvider;

    @Autowired
    private PostgresqlSchemaInfoReader postgresqlSchemaInfoReader;

    @Autowired
    private PostgresqlTableInfoReader postgresqlTableInfoReader;

    @Autowired
    private PostgresqlTableColumnInfoReader postgresqlTableColumnInfoReader;

    public List<SchemaDTO> listDatabaseSchemas(Connection connection, DatabaseType databaseType) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getDatabaseQueriesProvider(databaseType).createListAllSchemasQuery());

        return getSchemaInfoReader(databaseType).readInfo(result);
    }

    public List<TableDTO> listDatabaseTables(Connection connection, DatabaseType databaseType,
                                                 String schema) throws SQLException {
        ResultSet result;

        if (schema.isEmpty()) {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(getDatabaseQueriesProvider(databaseType).createListAllTablesQuery());
        } else {
            PreparedStatement listTablesFromSchema =
                    connection.prepareStatement(getDatabaseQueriesProvider(databaseType).createListAllTablesFromSchemaQuery());
            listTablesFromSchema.setString(1, schema);
            result = listTablesFromSchema.executeQuery();
        }

        return getTableInfoReader(databaseType).readInfo(result);
    }

    public List<TableColumnDTO> listDatabaseTableColumns(Connection connection, DatabaseType databaseType,
                                                          String schema, String table) throws SQLException {
        PreparedStatement listTablesFromSchema =
                connection.prepareStatement(getDatabaseQueriesProvider(databaseType).createListAllColumnsFromTableQuery());
        listTablesFromSchema.setString(1, schema);
        listTablesFromSchema.setString(2, table);

        ResultSet result = listTablesFromSchema.executeQuery();

        return getTableColumnInfoReader(databaseType).readInfo(result);
    }

    public List<Map<String, String>> loadTableView(Connection connection, DatabaseType databaseType,
                                                   String schema, String table, String columns, String orderBy,
                                                   String limit) throws SQLException {

        List<Map<String, String>> recordList = new ArrayList<>();

        String sqlQuery = createTableViewQuery(schema, table, columns, orderBy, limit,
                                               getDatabaseQueriesProvider(databaseType));

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sqlQuery);

        LinkedList<String> columnNames = readResultSetColumnNames(result);

        while (result.next()) {
            LinkedHashMap<String, String> record = new LinkedHashMap<>();

            for (String columnName : columnNames) {
                record.put(columnName, result.getString(columnName));
            }

            recordList.add(record);
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

    private DatabaseQueriesProvider getDatabaseQueriesProvider (DatabaseType databaseType) {

        switch (databaseType) {
            case POSTGRESQL:
                return postgresqlQueriesProvider;
            case ORACLE:
                break;
            case MYSQL:
                break;
        }

        throw new InvalidParameterException(String.format("Database type %s not supported", databaseType));
    }

    private DbInfoReader<SchemaDTO> getSchemaInfoReader (DatabaseType databaseType) {

        switch (databaseType) {
            case POSTGRESQL:
                return postgresqlSchemaInfoReader;
            case ORACLE:
                break;
            case MYSQL:
                break;
        }

        throw new InvalidParameterException(String.format("Database type %s not supported", databaseType));
    }

    private DbInfoReader<TableDTO> getTableInfoReader (DatabaseType databaseType) {

        switch (databaseType) {
            case POSTGRESQL:
                return postgresqlTableInfoReader;
            case ORACLE:
                break;
            case MYSQL:
                break;
        }

        throw new InvalidParameterException(String.format("Database type %s not supported", databaseType));
    }

    private DbInfoReader<TableColumnDTO> getTableColumnInfoReader (DatabaseType databaseType) {

        switch (databaseType) {
            case POSTGRESQL:
                return postgresqlTableColumnInfoReader;
            case ORACLE:
                break;
            case MYSQL:
                break;
        }

        throw new InvalidParameterException(String.format("Database type %s not supported", databaseType));
    }
}
