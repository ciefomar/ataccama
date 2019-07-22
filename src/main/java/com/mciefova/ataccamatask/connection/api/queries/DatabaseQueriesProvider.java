package com.mciefova.ataccamatask.connection.api.queries;

public interface DatabaseQueriesProvider {

    String createListAllSchemasQuery ();
    String createListAllTablesQuery ();
    String createListAllTablesFromSchemaQuery ();
    String createListAllColumnsFromTableQuery ();
    String createLoadTableViewQuery (String schemaName, String tableName);
    String createSelectQuery (String schemaName, String tableName, String columns, String orderBy, String limit);
}
