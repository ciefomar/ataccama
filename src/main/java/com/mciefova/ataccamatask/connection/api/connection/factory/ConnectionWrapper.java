package com.mciefova.ataccamatask.connection.api.connection.factory;

import com.mciefova.ataccamatask.connection.api.dto.SchemaDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableColumnDTO;
import com.mciefova.ataccamatask.connection.api.dto.TableDTO;
import com.mciefova.ataccamatask.connection.api.queries.DatabaseQueriesProvider;
import com.mciefova.ataccamatask.connection.api.service.reader.DbInfoReader;

import java.sql.Connection;

public class ConnectionWrapper {

    private Connection connection;
    private DbInfoReader<SchemaDTO> schemaInfoReader;
    private DbInfoReader<TableDTO> tableInfoReader;
    private DbInfoReader<TableColumnDTO> tableColumnInfoReader;
    private DatabaseQueriesProvider databaseQueriesProvider;

    public ConnectionWrapper(Connection connection,
                             DbInfoReader<SchemaDTO> schemaInfoReader,
                             DbInfoReader<TableDTO> tableInfoReader,
                             DbInfoReader<TableColumnDTO> tableColumnInfoReader,
                             DatabaseQueriesProvider databaseQueriesProvider) {
        this.connection = connection;
        this.schemaInfoReader = schemaInfoReader;
        this.tableInfoReader = tableInfoReader;
        this.tableColumnInfoReader = tableColumnInfoReader;
        this.databaseQueriesProvider = databaseQueriesProvider;
    }

    public Connection getConnection() {
        return connection;
    }

    public DbInfoReader<SchemaDTO> getSchemaInfoReader() {
        return schemaInfoReader;
    }

    public DbInfoReader<TableDTO> getTableInfoReader() {
        return tableInfoReader;
    }

    public DbInfoReader<TableColumnDTO> getTableColumnInfoReader() {
        return tableColumnInfoReader;
    }

    public DatabaseQueriesProvider getDatabaseQueriesProvider() {
        return databaseQueriesProvider;
    }
}
