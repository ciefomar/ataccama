package com.mciefova.ataccamatask.connection.api.queries;

import org.springframework.stereotype.Service;

@Service
public class PostgresqlQueriesProvider implements DatabaseQueriesProvider{

    private static final String LOAD_ALL_SCHEMAS = "SELECT * FROM information_schema.schemata";
    private static final String LOAD_ALL_TABLES = "SELECT * FROM information_schema.tables";
    private static final String LOAD_ALL_TABLES_FROM_SCHEMA = "SELECT * FROM information_schema.tables WHERE table_schema = ?";
    private static final String LOAD_ALL_TABLE_COLUMNS = "SELECT * FROM information_schema.columns WHERE table_schema = ? AND table_name = ?";
    private static final String LOAD_TABLE_VIEW_TEMPLATE = "SELECT * FROM %s.%s";

    private static final String SELECT = "SELECT";
    private static final String FROM = "FROM";
    private static final String LIMIT = "LIMIT";
    private static final String ORDER_BY = "ORDER BY";


    @Override
    public String createListAllSchemasQuery() {
        return LOAD_ALL_SCHEMAS;
    }

    @Override
    public String createListAllTablesQuery() {
        return LOAD_ALL_TABLES;
    }

    @Override
    public String createListAllTablesFromSchemaQuery() {
        return LOAD_ALL_TABLES_FROM_SCHEMA;
    }

    @Override
    public String createListAllColumnsFromTableQuery() {
        return LOAD_ALL_TABLE_COLUMNS;
    }

    @Override
    public String createLoadTableViewQuery(String schemaName, String tableName) {
        if (schemaName != null && !schemaName.isEmpty()
                && tableName!= null && !tableName.isEmpty()) {
            return String.format(LOAD_TABLE_VIEW_TEMPLATE, schemaName, tableName);
        } else {
            throw new IllegalArgumentException("Schema or table name is missing");
        }
    }

    /**
     * Build select query according to given parameters
     * @param schemaName
     * @param tableName
     * @param columns
     * @param orderBy
     * @param limit
     * @return select query
     */
    @Override
    public String createSelectQuery(String schemaName, String tableName, String columns, String orderBy, String limit) {
        if (schemaName != null && !schemaName.isEmpty() && tableName!= null && !tableName.isEmpty()) {
            StringBuilder query = new StringBuilder();

            query.append(SELECT).append(" ");

            if (columns != null && !columns.isEmpty()) {
                query.append(columns).append(" ");
            } else {
                query.append("*").append(" ");
            }

            query.append(FROM).append(" ").append(schemaName).append(".").append(tableName);

            if (orderBy != null && !orderBy.isEmpty()) {
                query.append(" ").append(ORDER_BY).append(" ").append(orderBy).append(" ");
            }

            if (limit != null && !limit.isEmpty()) {
                query.append(" ").append(LIMIT).append(" ").append(limit);
            }

            return query.toString();
        } else {
            throw new IllegalArgumentException("Schema or table name is missing");
        }
    }
}
